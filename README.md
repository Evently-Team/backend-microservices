
<h1 align="center">Evently (Backend)</h1>

---

<p align="center">
<img src="banner.png" width="80%">
</p>

## Overview

Evently is a platform designed to bring people together by simplifying event discovery, planning, and spontaneous meetups within a city. Users can explore public events like concerts or festivals, view private group activities for clubs or organizations, and even organize personal gatherings with friends. With an interactive city event feed, a personalized recommendation system, and a map of major events, Evently helps users connect through shared interests, discover local happenings, and make meaningful connections.

## Tecnologies Used

---

- Java
- Spring Boot
- Spring Security
- Hibernate
- JDBC
- MongoDB
- PostgreSQL
- Reddis
- Kafka
- Docker

## Architecture

---

Evently's backend architecture is designed for high scalability, real-time responsiveness, and efficient data handling across a variety of use cases in social event management. Below are the primary architectural components:

### API Gateway

The API Gateway serves as the single entry point for all client requests, managing routing, load balancing, authentication, and rate limiting. By consolidating external access to Evently’s services, the gateway streamlines traffic flow and provides a unified access layer, ensuring that requests reach the appropriate services efficiently.

### Microservices

Evently is built using a microservices architecture, where each service is responsible for a specific domain, such as user management, event scheduling, recommendation engine, and notifications. This decoupling allows each service to be independently developed, scaled, and deployed, enhancing modularity and maintainability.
Microservices in Evently's backend include:

- *Gateway Service*: Responsible for request routing.
- *User Service*: Responsible for managing user accounts, profiles and friends.
- *Event Service*: Responsible for managing events.
- *Event Management Service*: Responsible for helping with creating and removing events by delegating tasks such as: sending notifications to friends about new event, creating a new group chat related to the event and more.
- *Map Service*: Responsible for validating places provided in events data and getting their names from Google Map API.
- *Messenger Service*: Responsible for managing groups and chats.
- *Mail Delivery Service*: Responsible for sending mail messages to users.
- *Notification Service*: Responsible for sending notifications to users.

### Communication

Services within Evently communicate via a message queue, implemented with Kafka, for asynchronous, decoupled interactions. This setup enables seamless communication between services, supports real-time event notifications, and allows data pipelines to operate independently of individual service failures. For synchronous calls, services use REST or gRPC, depending on latency and protocol requirements.


### Data storage

- Replication (PostgreSQL): For user data, a master-slave replication model is used in PostgreSQL. The master node handles write requests, while read replicas (slaves) balance the read load, improving response times for read-heavy operations and ensuring availability.
- Sharding + Replication (MongoDB cluster): Given the vast number of events and the need for high availability, MongoDB is configured with both sharding and replication. Sharding distributes data across nodes to handle large volumes, while replica sets within each shard ensure fault tolerance and reliability.

### Containerization

Evently's microservices are containerized using Docker, allowing consistent, isolated runtime environments and facilitating deployment across various environments.

This architecture ensures that Evently can scale with user demand, provide low-latency access to both user and event data, and deliver a responsive experience to users in real-time event discovery and planning.

## Creators

---

- Adi Salimgereyev - [Github](https://github.com/abs0luty)
