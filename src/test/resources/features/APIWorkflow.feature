Feature: Syntax API HRMS Flow

  Background:
    Given a JWT is generated

  @api @t
  Scenario: Creating the employee using API
    Given a request is prepared for creating an employee
    When a POST call is made to create an employee
    Then the status code for creating qn employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as a global variable

  @api @t
  Scenario: Get the created employee
    Given a request is prepared for retrieving an employee
    When a Get call is made to retrieve the employee
    Then the status code for this employee is 200
    And the employee id "employee.employee_id" must match with globally stored employee id
    And this employee data at "employee" object matches with the data used to create the employee
      |emp_firstname|emp_lastname|emp_middle_name|emp_gender|emp_birthday|emp_status|emp_job_title|
      |Lesia        |Firadi      |     ms        | Female   |1987-05-20  |Happy     |QA           |

  @json
  Scenario: Creating an employee using json body
     Given a request is prepared for creating an employee using json payload
     When a POST call is made to create an employee
     Then the status code for creating qn employee is 201
     And the employee created contains key "Message" and value "Employee Created"
     And the employee id "Employee.employee_id" is stored as a global variable

  @dynamic
  Scenario: Creating an employee using highly dynamic scenario
     Given a request is prepared for creating an employee with data "Lesia", "Firadi","ms","F","1987-05-20","Happy","QA"
     When a POST call is made to create an employee
     Then the status code for creating qn employee is 201
     And the employee created contains key "Message" and value "Employee Created"
     And the employee id "Employee.employee_id" is stored as a global variable

  @updateApi @t
  Scenario: Updating the employee using API
     Given a request is prepared for updating employee
     When a PUT call is made to update an employee
     Then the status code for updating an employee is 200
     And employee updated contains key "Message" and value "Employee record Updated"


  @updateApi @t
  Scenario: Get the updated employee
    Given a request is prepared for retrieving an employee
    When a Get call is made to retrieve the employee
    Then the status code for this employee is 200

    And this employee data at "employee" object matches with the data used to create the employee
      |emp_firstname|emp_middle_name|emp_lastname|emp_birthday|emp_gender|emp_job_title|emp_status|
      |LesiaSofiVer |     yes       | Dirana     |2013-10-01 |Female     |  Family     |Happy     |


  @updateEJSON @t
  Scenario: Updating the employee using JSON
    Given a request is prepared for updating employee using json payload
    When a PUT call is made to update an employee
    Then the status code for updating an employee is 200
    And employee updated contains key "Message" and value "Employee record Updated"


  @updateEDynamic @t
  Scenario: Updating the employee using highly dynamic scenario
    Given a request is prepared for updating employee with data "employee_id","LesiaSofiVer","yes","irana","2013-10-01","F","Family","Happy"
    When a PUT call is made to update an employee
    Then the status code for updating an employee is 200
    And employee updated contains key "Message" and value "Employee record Updated"
