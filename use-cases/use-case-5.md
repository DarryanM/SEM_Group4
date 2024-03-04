# USE CASE: 5 Produce a report on TOP N Populated countries in continents where N value is provided.

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *Data Analyst* I want to *produce a report on the TOP N Populated countries in continents* to allow *easy access to this population information.*__

### Scope

Organisation.

### Level

Primary task.

### Preconditions

We know the value. Database contains current world population data.

### Success End Condition

A report is available for the Organisation.

### Failed End Condition

No report is produced.

### Primary Actor

The Data Analyst.

### Trigger

A request for Top N Countries Population by continents information is sent to Research Department.

## MAIN SUCCESS SCENARIO

1. Organisation request Top N Countries In Continents Population Report.
2. Data Analyst able to extract report of Top N Countries grouped by regions sorted from largest to smallest population.
3. Data Analyst provides report to organisation.

## EXTENSIONS

3. **Role does not exist**:
    1. Data Analyst informs that no role exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
