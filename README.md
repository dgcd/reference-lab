# Reference project

- back: JDK 24, Spring Boot, Spring Cloud Config Server
- devops: GitLab CI, Kubernetes, Helm

## Prerequisites

- Add environment variables to Gradle and JUnit run configuration template in IDE:

```
GIT_USERNAME=git_config_user;GIT_PASSWORD=d9Hsn3HJ4m;
CONFIG_USERNAME=config_user;CONFIG_PASSWORD=config_password;
POSTGRES_STUDIES_USERNAME=##;POSTGRES_STUDIES_PASSWORD=####;POSTGRES_STUDIES_PORT=######;
```

- Additionally create databases:

```sql
create database reference_int_tests;
```
