# USE CASE: 11 Produce a report on All the cities in district organised by largest to smallest population

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *Data Analyst* I want to *produce a report on all the cities in a district organised by largest to smallest population*
to allow easy access to this population information.

### Scope

Organisation.

### Level

Primary task.

### Preconditions

We know the role.  Database contains current world population data.

### Success End Condition

A report is available for the Data Analyst.

### Failed End Condition

No report is produced.

### Primary Actor

Data Analyst.

### Trigger

A request for the city population information of a district is sent to Research Department.

## MAIN SUCCESS SCENARIO

1. Organisation requests Population Report on all the cities in a district.
2. Data Analyst extracts report sorted from largest to smallest population.
3. Data Analyst provides report to the organisation.



## EXTENSIONS

3. **Role does not exist**:
    1. Data Analyst informs that no role exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0