# EmployeeReimbursement

Expense Reimbursement System
Executive Summary
The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.
State-chart Diagram (Reimbursement Statuses)

Reimbursement Types
Employees must select the type of reimbursement as: LODGING, TRAVEL, FOOD, or OTHER.

Technical Requirements
The back-end system shall connect to a PostgreSQL database using JDBC. The middle tier shall use Javalin for handling HTTP requests. The front-end view can use vanilla JavaScript (multi-page application) or Angular (single page application) that will call server-side components and render necessary data through DOM manipulation. Passwords shall be hashed and securely stored in the database. Users can upload a document or image of their receipt when submitting reimbursements.
