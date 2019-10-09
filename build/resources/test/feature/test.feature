Feature:


  Scenario:
    Given I have Jsonplaceholder 'https://jsonplaceholder.typicode.com/'
    And I have jsonplaceholderPost 'posts/1'
    When I make a get post request
    Then it should return title 'sunt aut facere repellat provident occaecati excepturi optio reprehenderit'


  @runit
    Scenario Outline:
      Given I am on booking site <baseUrl>
      When I supply the <api>, <key>
      And I <create> a booking
      Then I am able to delete the booking

      Examples:
      |baseUrl                          | create | api          |key                            |
      |http://hotel-test.equalexperts.io| booking| authorization| Basic YWRtaW46cGFzc3dvcmQxMjM=|