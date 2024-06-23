# Reference project

- back: JDK 21, Spring Boot, Spring Cloud Config Server
- devops: GitLab CI, Kubernetes, Helm

## Prerequisites

- Add environment variables to Gradle and JUnit run configuration template in IDE:

```
GIT_PASSWORD=dummy;CONFIG_USERNAME=dummy;GIT_USERNAME=dummy;CONFIG_PASSWORD=dummy;
POSTGRES_STUDIES_USERNAME=##;POSTGRES_STUDIES_PASSWORD=####;POSTGRES_STUDIES_PORT=######
```

- Additionally create databases:

```sql
create database reference_int_tests;
```
