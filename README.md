# GovBID 
A Better Way to Contract.

## Instructions on Compiling and Running
   Compile the project in an IDE by clicking the build option. To compile and run the test cases, ensure you have JUnit configured in your project and include the JUnit library in the classpath.

## Team Members
- Ovi Bhagwat: Submitted to Vocareum
- Sarah Stone
- Ana Farmus
- Saahil Kajarekar

## Class Descriptions

### User
- Account management with authentication, ratings, and messaging
- Tested: Account creation/deletion, rating calculations, message sending between users, and equals() comparison
- Relationships: Parent of Solicitor/Contractor, participates in Chats

### Solicitor
- Creates/manages contracts and processes contractor payments
- Tested: Contract posting, payment transactions, contract closing, and toString() formatting
- Relationships: Owns Contracts, interacts with Contractors via Bids

### Contractor
- Submits bids on contracts and completes awarded work
- Tested: Bid creation/management, payment receiving, contracts won tracking, and bidsUnderReview() filtering
- Relationships: Submits Bids, awarded Contracts from Solicitors

### Contract
- Defines work requirements and manages bidding process
- Tested: Bid addition checking, status changes, deadline, and winning bid assignment
- Relationships: Contains Bids, owned by one Solicitor

### Bid
- Represents contractor's proposal with pricing/status
- Tested: Status changes, isAccepted() checking, requested pay updates, and toString() formatting
- Relationships: Links one Contractor to one Contract

### Chat
- Enables messaging between two system users
- Tested: Message addition check, participant verification, chat ID generation, and getting user list
- Relationships: Contains Messages, connects two Users

### Message
- Individual communication with timestamp verification
- Tested: Content check, timestamp formatting, sender/recipient check, and bid attachment handling
- Relationships: Belongs to Chat, optionally references Bid

### Notification
- System-generated alerts about contract updates
- Tested: Timestamp generation check, message content saving, and recipient targeting
- Relationships: Sent to specific User about Bid/Contract

### Industry (Enum)
- Classifications for contractors
- Relationships: Used only by Contractor instances

### Database
- Storage for all application and user data
- Tested: Singleton enforcement, User and chats creation/deletion/editing, and search functionality
- Relationships: Contains and manages all Users, Contracts, Bids, Chats, and Messages data

### Server
- Deals with client to client and client to database interactions
- Tested: Not Required
- Relationships: Communicates with many Client instances, and interacts with Database

### Client
- Manages communication with server, and sending/receiving messages
- Tested: Not Required
- Relationships: Communicates with Server, and interacts with Solicitor, Contractor, and Database classes

### InvalidBidException
- Thrown when invalid Bid objects are detected
- Usage: Checks bid-related operations throughout the system

### InvalidChatException
- Thrown when invalid Chat objects are detected
- Usage: Checks chat-related operations and message exchanges

### InvalidContractException
- Thrown when invalid Contract objects are detected
- Usage: Checks contract creation and management operations

### InvalidUserException
- Thrown when invalid User objects are detected
- Usage: Checks user account operations and authentication