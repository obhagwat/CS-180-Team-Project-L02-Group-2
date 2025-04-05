# GovBID 
A Better Way to Contract.

## Team Members
- Ovi Bhagwat - Submitted in Vocareum workspace
- Sarah Stone
- Ana Farmus
- Saahil Kajarekar

## Instructions on Compiling and Running
   To compile, ensure you have JUnit configured in your project, and you included the JUnit library in the classpath. To run the test cases, ensure you have JUnit configured in your project.
   Compile the test classes, ensuring you include the JUnit library in the classpath.

## Class Descriptions

### `User`

**Functionality:** Represents a user in the system, handling operations such as username and profile management, friend and block lists, and storing conversation history.

**Testing:** `TestUser` verifies all functionalities, focusing on constructor behavior, getters and setters, profile changes, and reading/writing user data from/to files.

**Relationships:** Interacts with `Message`, `PhotoMessage`, and `Conversation` classes for managing conversations and messages.

### `Message`

**Functionality:** Models a text or photo message, encapsulating details like sender, receiver, content, and timestamp.

**Testing:** `TestMessage` covers construction, serialization/deserialization, and equality checks to ensure message integrity and functionality.

**Relationships:** Used by `Conversation` for storing individual messages. `PhotoMessage` extends `Message` to add support for images.

### `PhotoMessage`

**Functionality:** Extends `Message` to support photo messages, including loading and saving images.

**Testing:** `TestPhotoMessage` assesses image loading, saving, and the correct generation of filenames based on message metadata.

**Relationships:** Inherits from `Message` and utilized within `Conversation` for photo-based communication.

### `Conversation`

**Functionality:** Manages a conversation between two users, including message history and reading/writing conversation data.

**Testing:** `TestConversation` ensures correct conversation file naming, message addition and deletion, and data persistence through read/write operations.

**Relationships:** Aggregates `Message` and `PhotoMessage` instances. Relies on `User` to identify conversation participants.

### `DatabaseManage`

**Functionality:** Provides static methods for user account management (creation, login) and conversation initiation between users.

**Testing:** `TestDatabaseManage` evaluates account creation, login procedures, and the establishment of conversations, including error handling and edge cases.

**Relationships:** Works closely with `User` and `Conversation` classes to orchestrate higher-level operations like account and conversation management.

[Project Guide doc](https://docs.google.com/document/d/1TrToqI1D0JqabIITiFDCbOp5mhsz4vfsDnJ16f8bZbs/edit?tab=t.0)
