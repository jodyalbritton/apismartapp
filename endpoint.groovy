/**
 *  Copyright 2015 SmartThings
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 *  Developer API
 *
 *  Author: SmartThings
 */

    import groovy.json.JsonBuilder
    definition(
        name: "Developer API",
        namespace: "smartthings",
        author: "SmartThings",
        description: "SmartApp API for developers",
        category: "My Apps",
        iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
        iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
        oauth: [displayName: "SmartThings Developer API", displayLink: ""])

    preferences {
        section("Allow Endpoint to Control These Things...") {

            input "switches", "capability.switch", title: "Which Switches?", multiple: true, required: false
            input "dimmers", "capability.switchLevel", title: "Which Dimmers?", multiple: true, required: false
            input "thermostats", "capability.thermostat", title: "Which Thermostats?", multiple: true, required: false
            input "motions", "capability.motionSensor", title: "Which Motions?", multiple: true, required: false
            input "accelerations", "capability.accelerationSensor", title: "Which Accelerations?", multiple: true, required: false
            input "contacts", "capability.contactSensor", title: "Which Contacts?", multiple: true, required: false
            input "illuminants", "capability.illuminanceMeasurement", title: "Which Illuminance Sensors?", multiple: true, required: false
            input "temperatures", "capability.temperatureMeasurement", title: "Which Temperatures?", multiple: true, required: false
            input "humidities", "capability.relativeHumidityMeasurement", title: "Which Humidities?", multiple: true, required: false
            input "presences", "capability.presenceSensor", title: "Which Presence?", multiple: true, required: false
            input "locks", "capability.lock", title: "Which Locks?", multiple: true, required: false
            input "batteries", "capability.battery", title: "Which Batteries?", multiple: true, required: false
            input "powers", "capability.powerMeter", title: "Power Meters", required:false, multiple: true
            input "energys", "capability.energyMeter", title: "Energy Meters", required:false, multiple: true
        }

        /*********************************************************************
        * Create storage for values that will be later evaluated. We need to
        * only expose the parts of the API that have been authorized by the
        * user
        **********************************************************************/

        appSetting "switches"
        appSetting "dimmers"
        appSetting "thermostats"
        appSetting "motions"
        appSetting "accelerations"
        appSetting "contacts"
        appSetting "illuminants"
        appSetting "temperatures"
        appSetting "humidities"
        appSetting "presences"
        appSetting "locks"
        appSetting "batteries"
        appSetting "powers"
        appSetting "energys"
    }


    def installed() {
        initialize()
    }

    def updated() {
        unsubscribe()
        initialize()
    }

    def initialize() {

        /*********************************************************************
        * SUBSCRIPTIONS
        *
        * TODO: Case structure to evalute true/false values from the settings
        * created above. We will only subscribe to those devices where the
        * user has authorized access
        *
        *
        **********************************************************************/

        subscribe(switches, "switch", handleSwitchEvent)
        subscribe(dimmers, "level", handleSwitchLevelEvent)
        subscribe(motions, "motion", handleMotionEvent)
        subscribe(accelerations, "acceleration", handleAccelerationEvent)
        subscribe(contacts, "contact", handleContactEvent)
        subscribe(illuminants, "illuminance", handleIlluminanceEvent)
        subscribe(temperatures, "temperature", handleTemperatureEvent)
        subscribe(humidities, "humidity", handleHumidityEvent)
        subscribe(lock, "lock", handleDoorLockEvent)
        subscribe(batteries, "battery", handleBatteryEvent)
        subscribe(powers, "power", handlePowerEvent)
        subscribe(energys, "energy", handleEnergyEvent)
        subscribe(presence, "presence", handlePresenceEvent)


    }



    /*********************************************************************
    * EVENT HANDLERS
    *
    * TODO: Case structure to evalute true/false values from the settings
    * created above. We will only handle events where the user has
    * authorized access to the device
    **********************************************************************/
    def handleIlluminanceEvent(evt) {
        logField(evt) { it.toString() }
    }

    def handleHumidityEvent(evt) {
        logField(evt) { it.toString() }
    }

    def handleTemperatureEvent(evt) {
        logField(evt) { it.toString() }
    }

    def handleContactEvent(evt) {
        logField(evt) { it == "open" ? "1" : "0" }
    }

    def handleAccelerationEvent(evt) {
        logField(evt) { it == "active" ? "1" : "0" }
    }

    def handleMotionEvent(evt) {
        logField(evt) { it == "active" ? "1" : "0" }
    }

    def handleSwitchEvent(evt) {
        logField(evt) { it.toString() }
    }

    def handleSwitchLevelEvent(evt) {
        logField(evt) { it.toString() }
    }

    def handleDoorLockEvent(evt) {
        logField(evt) {it == "locked" ? "locked" : "unlocked" }
    }

    def handleBatteryEvent(evt) {
        logField(evt) { it.toString() }
    }

    def handlePowerEvent(evt) {
        logField(evt) { it.toString() }
    }


    def handleEnergyEvent(evt) {
        logField(evt) { it.toString() }
    }

    def handlePresenceEvent(evt) {
        logField(evt) { it.toString() }
    }


    /*********************************************************************
    * URL MAPPINGS
    *
    * TODO: Case structure to evalute true/false values from the settings
    * created above. We will only expose endpoints that have been
    * authorized by the user.
    *
    * We also need to create a "/devices" path that will iterate through
    * the map of allowed device types and allow the developer to discover
    * the available endpoints via the api.
    **********************************************************************/

    mappings {
        // location
    	path("/location") {
    		action: [
        		GET: "listLocation"
        	]
    	}
        // modes
        path("/modes") {
            action: [
                GET: "listModes"
            ]
        }
        path("/modes/:id") {
            action: [
                GET: "listModes",
                POST: "switchMode"
            ]
        }
    	// hub
    	path("/hubs") {
    		action: [
        		GET: "listHubs"
        	]
    	}
        path("/hubs/:id") {
            action: [
                GET: "listHubs"
            ]
        }
	    // GET device lists
	    path("/devices/:deviceType") {
		    action: [
			    GET: "listDevices"
		    ]
	    }
        // GET specific device info
        // PUT update the state of a device
        path("/devices/:deviceType/:id") {
            action: [
            	GET: "listDevices",
            ]
        }
        path("/devices/:deviceType/:id/commands") {
            action: [
                GET: "listDeviceCommands",
                POST: "sendDeviceCommand"
            ]
        }
        // GET event lists
        path("/devices/:deviceType/:id/events") {
        	action: [
            	GET: "listDeviceEvents"
            ]
        }
        // Routines
        path("/routines") {
            action: [
                GET: "listRoutines"
            ]
        }
        path("/routines/:id") {
            action: [
                GET: "listRoutines",
                POST: "executeRoutine"
            ]
        }
    }

    // hub is non-null
	def getHub(hub, explodedView = false) {
		def result = [:]
        ["id", "name"].each {
            result << [(it) : hub."$it"]
        }

        if(explodedView) {
            ["firmwareVersionString", "localIP", "localSrvPortTCP", "zigbeeEui", "zigbeeId"].each {
                result << [(it) : hub."$it"]
            }
            result << ["type" : hub.type as String]
        }
    	result
	}

	def getMode(mode, explodedView = false) {
    	def result = [:]
        ["id", "name"].each {
        	result << [(it) : mode."$it"]
        }

        if(explodedView) {
        	["locationId"].each {
            	result << [(it) : mode."$it"]
            }
        }
    	result
	}

	def listLocation() {
		log.debug "in listLocation, location: $location"

		def result = [:]
        ["contactBookEnabled", "name", "temperatureScale", "zipCode"].each {
            result << [(it) : location."$it"]
        }
        result << ["latitude" : location.latitude as String]
    	result << ["longitude" : location.longitude as String]
        result << ["timeZone" : location.timeZone?.getDisplayName()]
        result << ["currentMode" : getMode(location.currentMode)]

    	def hubs = []
    	location.hubs?.each {
    		hubs << getHub(it)
    	}
    	result << ["hubs" : hubs]

    	log.debug "locations to return: $result"
    	//render contentType: "application/json", statusCode: 200, data: groovy.json.JsonOutput.toJson(result)
        result
	}

	def listHubs() {
		log.debug "in listHubs, hubs: $location.hubs"
        def id = params.id
        if(id) {
        	def hub = location.hubs?.find{it.id == id}
            if(hub) {
            	getHub(hub, true)
            } else {
            	httpError(404, "hub not found")
            }
        } else {
        	def result = []
            location.hubs?.each {
                result << getHub(it)
            }
            result
        }
	}

    def listModes() {
        def id = params.id
        if(id) {
        	def themode = location.modes?.find{it.id == id}
            if(themode) {
            	getMode(themode, true)
            } else {
            	httpError(404, "mode not found")
            }
        } else {
        	def modes = []
            location.modes?.each {
                modes << getMode(it)
            }
            modes
        }
    }

    def switchMode() {
    	def id = params.id
        def mode = location.modes?.find{it.id == id}
        if(mode) {
        	location.setMode(mode.name)
            render contentType: "text/html", status: 204, data: "No Content"
        } else {
        	httpError(404, "mode not found")
        }
    }

    def listDevices() {
		def type = params.deviceType
        if(!settings[type]) {
        	httpError(405, "Method Not Allowed")
        }
        def id = params.id
        if(id) {
            def device = settings[type]?.find{it.id == id}
        	deviceItem(device, true)
        } else {
			settings[type]?.collect{deviceItem(it, false)} ?: []
        }
	}

    def listDeviceEvents() {
    	def type = params.deviceType
        def id = params.id
        def device = settings[type]?.find{it.id == id}

        if (!device) {
            httpError(404, "Device not found")
        } else {
            log.debug device.getProperties()
            def events = device.events(max: 20)
            def result = events.collect{item(device, it)}
            result
        }
    }

    private item(device, s) {
        log.debug s.getProperties()
        device && s ? [device_id: device.id, label: device.displayName, name: s.name, value: s.value, date: s.date, stateChange: s.stateChange, eventSource: s.eventSource] : null
    }

    def listDeviceCommands() {
        def type = params.deviceType
        def id = params.id
        def device = settings[type]?.find{it.id == id}
        def commands = []
        if(!device) {
            httpError(404, "Device not found")
        } else {
            device.supportedCommands?.each {
                commands << ["command" : it.name, "params"  : [:]]
            }
        }
        commands
    }

    def sendDeviceCommand() {
    	def type = params.deviceType
        def id = params.id
    	def command = request.JSON?.command
        if(!command) {
            httpError(404, "Device not found")
        }

        def device = settings[type]?.find{it.id == params.id}
        if(!device) {
        	httpError(404, "Device not found")
        } else {
            log.debug "Executing command: $command on device: $device"
            device."$command"()
            render contentType: "text/html", status: 204, data: "No Content"
        }
    }

	def deviceItem(device, explodedView) {
		if (!device) return null
   		def results = [:]
        ["id", "name", "displayName"].each {
            results << [(it) : device."$it"]
        }

        if(explodedView) {
    		def attrsAndVals = [:]
            log.debug device.getProperties()
   			device.supportedAttributes?.each {
       			attrsAndVals << [(it.name) : device.currentValue(it.name)]
    		}

    		results << ["attributes" : attrsAndVals]
        }
    	results
	}

    def listRoutines() {
        def id = params.id
        def results = []
        if(id) {
            def routine = location.helloHome?.getPhrases().find{it.id == id}
            def myRoutine = [:]
            if(!routine) {
                httpError(404, "Routine not found")
            } else {
                ["id", "label"].each {
                    myRoutine << [(it) : routine."$it"]
                }
                myRoutine
            }
        } else {
            location.helloHome?.getPhrases().each { routine ->
                def myRoutine = [:]
                ["id", "label"].each {
                    myRoutine << [(it) : routine."$it"]
                }
                results << myRoutine
            }
            results
        }
    }

    def executeRoutine() {
    	def id = params.id
        def routine = location.helloHome?.getPhrases().find{it.id == id}
        if(!routine) {
        	httpError(404, "Routine not found")
        } else {
        	location.helloHome?.execute(routine.label)
            render contentType: "text/html", status: 204, data: "No Content"
        }
    }

    private device(it, type) {
        it ? [id: it.id, label: it.label, type: type] : null
    }

    /*********************************************************************
    * CALLBACK TO URL
    *
    * TODO: We could call back to a url that is built in to the smartapp
    * and create method to allow subscription to events. If websockets
    * are available that would be the optimal method. Otherwise we could
    * create a setting that would allow the developer to specify a callback
    * url for sending updates.
    *
    **********************************************************************/
    private logField(evt) {
        httpPostJson(uri: "#####URI#####",   body:[device: evt.deviceId, name: evt.name, value: evt.value, date: evt.isoDate, unit: evt.unit]) {
            log.debug evt.name+" Event data successfully posted"
        }
    }
