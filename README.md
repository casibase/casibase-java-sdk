# casibase-java-sdk

[![GitHub Actions](https://github.com/casibase/casibase-java-sdk/actions/workflows/maven-ci.yml/badge.svg)](https://github.com/casibase/casibase-java-sdk/actions/workflows/maven-ci.yml)
[![codebeat badge](https://codebeat.co/badges/e3e92eff-8b71-4903-9764-5126e855b3b6)](https://codebeat.co/projects/github-com-casibase-casibase-java-sdk-master)
[![codecov](https://codecov.io/gh/casibase/casibase-java-sdk/branch/master/graph/badge.svg?token=1C2FSTN4J8)](https://codecov.io/gh/casibase/casibase-java-sdk)
[![Javadocs](https://www.javadoc.io/badge/org.casbin/casibase-java-sdk.svg)](https://www.javadoc.io/doc/org.casbin/casibase-java-sdk)
[![Maven Central](https://img.shields.io/maven-central/v/org.casbin/casibase-java-sdk.svg)](https://mvnrepository.com/artifact/org.casbin/casibase-java-sdk/latest)
[![Release](https://img.shields.io/github/release/casibase/casibase-java-sdk.svg)](https://github.com/casibase/casibase-java-sdk/releases/latest)
[![Discord](https://img.shields.io/discord/1022748306096537660?logo=discord&label=discord&color=5865F2)](https://discord.gg/5rPsrAzK7S)

This is the Java SDK for Casibase, which allows you to easily call Casibase's API.

The Casibase SDK is very easy to use. We will demonstrate the usage steps below.

## Step 1: Initialize Configuration

Initialization requires 5 parameters, all of which are strings:

| Name (in order)  | Required | Description                                                  |
|------------------|----------|--------------------------------------------------------------|
| endpoint         | Yes      | Casibase server URL, e.g., `https://demo-admin.casibase.com` |
| clientId         | Yes      | Client ID                                                    |
| clientSecret     | Yes      | Client secret                                                |
| organizationName | Yes      | Name of the Casibase organization, e.g., `casbin`            |
| applicationName  | No       | Name of the Casibase application, e.g., `app-casibase`       |

## Step 2: Obtain and Use Services

Currently, the available service is: `TaskService`

You can create it like this:

```java
TaskService taskService = new TaskService(config);
```

## TaskService

`TaskService` supports basic task operations, such as:

- `getTask(String name)`: Get a single task by task name.
- `getTasks()`: Get all tasks under the organizationName.
- `addTask(Task task)/updateTask(Task task)/deleteTask(Task task)`: Write to the database.

## SpringBoot Support

Not available yet