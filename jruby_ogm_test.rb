require 'java'


CURRENT_PATH = File.expand_path File.dirname(__FILE__)
DML_CONF_PATH = File.join(CURRENT_PATH, 'src', 'common', 'dml')
OGM_CONF_PATH = File.join(CURRENT_PATH, 'config')
$CLASSPATH << OGM_CONF_PATH

# load the jars
LIB_PATH = File.join(CURRENT_PATH, 'lib')
$CLASSPATH << LIB_PATH
Dir[File.join(LIB_PATH, '*.jar')].each{|jar|
  #puts "Loading JAR: #{jar}"
  require jar
}

# load the domain model jar
DIST_PATH = File.join(CURRENT_PATH, 'dist')
$CLASSPATH << DIST_PATH
require File.join(DIST_PATH, 'theharvestar-domain.jar')


# Load Fenix Framework
FenixConfig = Java::PtIstFenixframework::Config
FenixFramework = Java::PtIstFenixframework::FenixFramework

# Load the domain models
CloudTmGame  = Java::ItAlgoTheharvestarDomain::Game
CloudTmAgent  = Java::ItAlgoTheharvestarDomain::Agent
CloudTmTerrain  = Java::ItAlgoTheharvestarDomain::Terrain
CloudTmRoot = Java::ItAlgoTheharvestarDomain::Root

# Load the CloudTM glue framework
CloudTmInit = Java::OrgCloudtmFramework::Init
CloudTmTxSystem = Java::OrgCloudtmFramework::TxSystem
CloudTmConfig = Java::OrgCloudtmFramework::CloudtmConfig

#CloudTmTransactionalCommand = Java::OrgCloudtmFramework::TransactionalCommand
#HibOgmTxManager = Java::OrgCloudtmFrameworkOgm::HibOgmTxManager


IllegalWriteException = Java::PtIstFenixframeworkPstm::IllegalWriteException
CommitException = Java::Jvstm::CommitException
WriteOnReadException = Java::Jvstm::WriteOnReadException
UnableToDetermineIdException = Java::PtIstFenixframeworkPstm::AbstractDomainObject::UnableToDetermineIdException


# In order to bypass the use of the constructor with closure, that causes problems
# in the jruby binding.
# Here we open the Fenix Config class and we define a method that permits to
# valorize the same protected variables managed by the standard constructor.
class FenixConfig
  # Accepts an hash of params, keys are instance variables of FenixConfig class
  # and values are used to valorize these variables.
  def init params
    params.each do |name, value|
      set_param(name, value)
    end
  end

  private

  # Sets an instance variable value.
  def set_param(name, value)
    # Jruby doesn't offer accessors for the protected variables.
    field = self.java_class.declared_field name
    field.accessible = true
    field.set_value Java.ruby_to_java(self), Java.ruby_to_java(value)
  end
end

class CloudTmTransactionManager
  #cattr_accessor :manager

  class << self
    def manager
      @manager
    end

    def manager=(man)
      @manager = man
    end
  end
end

# This is the Fenix Framework loader. It provides a simple way to
# run the framework initialization process.
class FenixLoader
  # Load the Fenix Framework.
  # Options:
  # => dml: the dml file name
  # => conf: the configuration file name
  # => root: the root class
  def self.load(options)
    config = FenixConfig.new
    config.init(
      :domainModelPath => File.join(DML_CONF_PATH, options[:dml]),
      :dbAlias => File.join(OGM_CONF_PATH, options[:conf]),
      :rootClass => CloudTmRoot.java_class,
      :repositoryType => FenixConfig::RepositoryType::INFINISPAN
    )

    CloudTmInit.initializeTxSystem(config, CloudTmConfig::Framework::OGM)
    CloudTmTransactionManager.manager = CloudTmTxSystem.getManager
  end
end


class CloudTmGame
  class << self

    def create attrs = {}
      manager = CloudTmTransactionManager.manager
      manager.withTransaction do

        instance = new
        attrs.each do |attr, value|
          instance.send("#{attr}=", value)
        end
        manager.save instance
        instance.set_root manager.getRoot
      end
    end

  end
end


class CloudTmAgent
  class << self
    def create group, attrs = {}
      manager = CloudTmTransactionManager.manager

        instance = new
        attrs.each do |attr, value|
          instance.send("#{attr}=", value)
        end
        manager.save instance
        group.addAgents(instance)

    end
  end

  def destroy
  end
end


class CloudTmTerrain
  class << self

    def create attrs = {}
      manager = CloudTmTransactionManager.manager
      manager.withTransaction do

        instance = new
        attrs.each do |attr, value|
          instance.send("#{attr}=", value)
        end
        manager.save instance
        #instance.set_root manager.getRoot
      end
    end

  end
end


FenixLoader.load({
    :dml => 'theharvestar.dml',
    :conf => 'infinispanNoFile.xml'
  })


_manager = CloudTmTransactionManager.manager

_manager.withTransaction do
  puts "Games before create: #{_manager.getRoot.getGames.count}"
end

CloudTmGame.create(:name => 'test-game')

_manager.withTransaction do
  puts "Games after create: #{_manager.getRoot.getGames.count}"
end

_manager.withTransaction do
  game = CloudTmGame.create(:name => 'test-game')
  terrain1 = CloudTmTerrain.create(:terrain_type => 'water')
  puts terrain1.terrain_type
end
