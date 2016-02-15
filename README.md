# API SmartApp

This API SmartApp is the first iteration of a restful SmartThings developer API. In it's current form, it is simply a web services SmartApp and can be installed and used as such. See [Web Services SmartApps](http://docs.smartthings.com/en/latest/smartapp-web-services-developers-guide/index.html) in the SmartThings developer documentation for more details.

## TODO list
This todo list is a running tally of what needs to be addressed. Please add to this todo list as you see fit.

- [X] Add README
- [] Use includes directive and try to separate out some code for readability and maintainability
- [] Use appropriate HTTP methods for operations
- [] Create a map of settings that allow for the selection of devices to ask the customer for
- [] a set of case statements will iterate through the map and only reveal the endpoints of approved devices.
- [] Need to implement general unauthorized scenarios
- []  I also want to create an endpoint that lists all of a users devices at once

## Installation
TODO

## API Definition
This is an example of what the API definition could look like.

| Endpoint | Methods | Returns |
| --- | --- | ---|
| /switches | GET | 200 Ok - A list of switches. [{},{},{}] |
|           |     | 401 Unathorized - Bad credentials |
| /switches/:id | GET | 200 Ok - The switch with the given `:id`. {} |
|               | POST - {} | 204 No Content - If the command was received successfully |
