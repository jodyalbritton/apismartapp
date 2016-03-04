# API SmartApp

This API SmartApp is the first iteration of a restful SmartThings developer API. In it's current form, it is simply a web services SmartApp and can be installed and used as such. See [Web Services SmartApps](http://docs.smartthings.com/en/latest/smartapp-web-services-developers-guide/index.html) in the SmartThings developer documentation for more details.

## TODO list
This todo list is a running tally of what needs to be addressed. Please add to this todo list as you see fit.

- [X] Add README
- [X] Use appropriate HTTP methods for operations
- [X] Create a map of settings that allow for the selection of devices to ask the customer for
- [X] Need to implement general unauthorized scenarios
- [ ] Create an endpoint that lists all of a users devices at once

## Installation
TODO

## API Definition
See https://smartthings.atlassian.net/wiki/display/~dave/Developer+API+Endpoint+Design for endpoint design.

## API Explorer Install
 - Latest version of Node
 - Latest version of Meteor 
 - Edit smartThingsApi.js and apply your client id and token (get your token via postman or other methods for now)
 
In the smartThingsApiExplorer folder run the command "meteor" 

Open a browser and use the address http://localhost:3000
