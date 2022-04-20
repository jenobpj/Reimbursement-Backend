# Expense Reimbursement System (ERS)

## Description
A system for adding and managing Employee Reimbursements.

## Technologies Used
- PostgresSQL
- Javalin
- Junit
- Mockito
- HTML
- JavaScript

## Features
Features already implemented
- Login functionality, the login will lead the user to the appropriate page based on their role within the company
- Filtering, all users can filter the reimbursements they see based on whether they are Pending, Reject, Accepted, or Everything
- Normal Employees can submit new requests for reimbursement, this includes a photograph of their receipt
- Finance Managers can choose to Accept or Reject pending requests.

To-do list:
- Password Hashing
- Support on GCP

## Getting Started
In its current state, the program runs locally. You will want to have dBeaver and IntelliJ installed, though you can build and launch the code from a CLI.
- Clone this repository
- Open your SQL editor. Make sure environment variables are set up. 
- Open the backend folder (named java) in IntelliJ, run the driver class. Alternately you can run this as a jar file
  - Open the backend parent folder (named java). 
  - Open that page in your CLI (like GitBash) run the command *./gradlew build*
  - Navigate in GitBash to Build/lib. There will be a jar file named java.1.0.SNAPSHOT.jar run type in and run *java -jar java-1.0-SNAPSHOT.jar* to run the jar file.
- Navigate to the front end folder, open the index.html file. It should open in your web browser.
## Usage
- Login with any of these provided users "Username: manhunter; Password: password" "Username: jenob; Password: passowrd" "Username: jinu; 

