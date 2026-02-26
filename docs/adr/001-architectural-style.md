# ADR 001: Architectural Style – Modular Monolith vs Microservices

## Status

Accepted

## Context

The Conference Management System (CMS) must support:

- Hundreds of speakers
- Dozens of event staff
- Thousands of attendees
- Multiple independently branded conferences
- Cloud-native deployment requirement
- Web-based access (no mobile app)
- No payment integration required

Core functional areas include:

- Schedule management
- Speaker talk management
- Attendee voting (up/down)
- Notifications for schedule changes
- Slide storage and controlled access
- Multi-channel evaluation system (web, email, SMS, phone)
- Multi-tenant conference branding

The system is expected to handle moderate load with peak usage during conference events.

An architectural decision is required regarding:

- **Microservices architecture**
  vs
- **Modular Monolith architecture**

This decision will significantly affect operational complexity, scalability strategy, development velocity, and long-term maintainability.

---

## Decision

We will implement the system as a **Modular Monolith**, deployed as a cloud-native containerized application.

The system will:

- Be deployed as a single deployable unit
- Be internally structured into clearly separated domain modules
- Enforce strong logical boundaries between modules
- Be designed to allow future extraction of services if required

The architecture will follow domain-driven modular design principles to maintain separation of concerns and long-term evolvability.

---

## Rationale

### 1. System Complexity Level

The domain is moderately complex but does not require distributed system boundaries at this stage.

- No high-frequency financial transactions
- No extreme horizontal scaling requirements
- No independent scaling needs per domain
- No real-time event streaming requirements

A distributed system would introduce unnecessary complexity.

---

### 2. Operational Overhead

A microservices architecture would require:

- Independent deployments per service
- Service-to-service communication management
- Distributed logging and tracing
- API gateway configuration
- Event-driven infrastructure
- Eventual consistency handling
- Complex CI/CD pipelines

Given expected scale and scope, this overhead is not justified.

---

### 3. Development Velocity

A modular monolith:

- Simplifies debugging
- Avoids network-related failure modes
- Supports transactional consistency
- Reduces DevOps burden
- Accelerates feature delivery

This aligns better with expected project scale and team size.

---

### 4. Cloud-Native Requirement

Cloud-native does not require microservices.

The system will still be:

- Containerized (Docker)
- Deployed via managed cloud services
- Horizontally scalable
- Using managed database and object storage
- Using managed messaging/notification services

Thus, cloud-native objectives are fully satisfied without microservices.

---

### 5. Evolutionary Architecture Strategy

The modular monolith will be structured so that modules:

- Have clear ownership of domain logic
- Avoid direct cross-module data access
- Communicate via internal interfaces or domain events

If scaling or organizational needs change, specific modules (e.g., Voting or Notification) can be extracted into standalone services.

This supports an incremental migration path rather than premature distribution.

---

## Alternatives Considered

### Option A: Full Microservices Architecture

Each core domain implemented as an independent service:

- Auth Service
- Conference Service
- Schedule Service
- Speaker Service
- Voting Service
- Evaluation Service
- Notification Service
- Media Service

#### Advantages

- Independent scaling
- Independent deployments
- Strong service isolation
- Technology heterogeneity possible
- Organizational scalability

#### Disadvantages

- High operational complexity
- Distributed data consistency challenges
- Increased infrastructure cost
- Higher cognitive load
- Slower initial delivery
- Requires mature DevOps practices

This option was rejected as premature for the current scope and scale.

---

### Option B: Layered Monolith (Non-Modular)

Traditional monolith without enforced modular boundaries.

#### Advantages

- Simpler initial setup
- Minimal infrastructure

#### Disadvantages

- Risk of tight coupling
- Difficult future extraction
- Reduced long-term maintainability
- Higher risk of "big ball of mud"

This option was rejected due to long-term maintainability risks.

---

## Consequences

### Positive

- Faster time-to-market
- Lower operational complexity
- Simplified transactions and data consistency
- Easier debugging and testing
- Reduced infrastructure cost
- Easier onboarding for developers
- Clear path to future service extraction

### Negative

- Entire system deployed as single unit
- Scaling is at application level, not per module
- Requires discipline to maintain module boundaries
- Future extraction will require refactoring effort

---

## Implementation Notes

The modular monolith will:

- Use clear domain-based module separation
- Prevent cross-module direct database access
- Enforce access through well-defined interfaces
- Use containerized deployment
- Support horizontal scaling
- Include observability from the beginning
- Use managed cloud services for database, storage, and messaging

---

## Future Review

This decision should be revisited if:

- The number of concurrent conferences grows significantly
- The system evolves into a multi-organization SaaS platform
- Independent development teams are introduced
- Specific modules require independent scaling beyond the rest of the system
- Operational maturity increases significantly

---

## Decision Outcome

Chosen architecture: **Modular Monolith (Cloud-Native Deployment)**

This approach balances scalability, maintainability, and operational simplicity while preserving future flexibility.
