# USE CASE: 12 Produce a report on the top "N" populated cities in the world

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *Data Analyst* I want to *produce a report on the top "N" populated cities in the world, with "N" provided*, to allow easy access to this population information.

### Scope

Organisation

### Level

Primary task.

### Preconditions

We know the value.  Database contains current world population data.

### Success End Condition

A report is available for the Data Analyst

### Failed End Condition

No report is produced.

### Primary Actor

Data Analyst.

### Trigger

A request for the top "N" populated cities in the world is sent to Research Department.

## MAIN SUCCESS SCENARIO

1. Organisation requests Population Report on the top "N" populated cities in the world.
2. Data Analyst extracts report sorted from largest to smallest population.
3. Data Analyst provides report to the organisation.


## EXTENSIONS

3. **Role does not exist**:
    1. Data Analyst informs that no role exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
