#!/usr/bin/env ruby


require 'sling/sling'
require 'sling/test'
require 'sling/message'
require 'test/unit.rb'
include SlingInterface
include SlingUsers
include SlingSites
include SlingMessage

class TC_MyMessageTest < Test::Unit::TestCase
  include SlingTest

  def test_hello_world
	@s.log.level = Logger::INFO
    m = Time.now.to_i.to_s
	path = "/hello_world/#{m}"
	n = create_node(path,{ "sling:resourceType" => "sakai/helloworld" })
	@s.log.info("Created node #{n}")
	helloprops = @s.get_node_props(path)
	@s.log.info("Props are #{helloprops} ")
	assert_equal(path,helloprops["nodepath"])
	assert(helloprops["message"].index("sort ya") > 0)

  end


end


