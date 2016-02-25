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
                GET: "listModes"
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
	    path("/:deviceType") {
		    action: [
			    GET: "list"
		    ]
	    }
        // GET specific device info
        // PUT update the state of a device
        path("/:deviceType/:id") {
            action: [
            	GET: "list",
                POST: "updateDevice"
            ]
        }
        // GET event lists
        path("/:deviceType/:id/events") {
        	action: [
            	GET: "listEvents"
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

	def getMode(mode) {
        log.debug mode.getProperties()
		def themode = ["id" : mode.id, "name" : mode.name]
    	log.debug "in getMode will return $themode"
    	themode
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
		def result = []
        def id = params.id
        if(id) {
            location.hubs?.each {
                if(it.id == id) {
                    result << getHub(it, true)
                }
            }
        } else {
            location.hubs?.each {
                result << getHub(it)
            }
        }
    	log.debug "will return: $result"
        result
	}

    def listModes() {
        def modes = []
        def id = params.id
        if(id) {
            location.modes?.each {
                if(it.id == id) {
                    modes << getMode(it, true)
                }
            }
        } else {
            location.modes?.each {
                modes << getMode(it)
            }
        }
        modes
    }

    def list() {
		log.debug "[PROD] list, params: ${params}"
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

    def listEvents() {
    	def type = params.deviceType
        def id = params.id
        def device = settings[type]?.find{it.id == params.id}

        if (!device) {
            httpError(404, "Device not found")
        } else {
            def events = device.events(max: 40)
            events = events.findAll{it.name == type}
            def result = events.collect{item(device, it)}
            result
        }
    }

    def updateDevice() {
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
            def commands = []
            log.debug device.getProperties()
   			device.supportedAttributes?.each {
       			attrsAndVals << [(it.name) : device.currentValue(it.name)]
    		}
            device.supportedCommands?.each {
            	commands << it.name
            }

    		results << ["attributes" : attrsAndVals]
            results << ["commands" : commands]
        }
    	results
	}

    private void update(devices) {
        log.debug "update, request: params: ${params}, devices: $devices.id"


        //def command = request.JSON?.command
        def command = params.command
        def level = params.level
        //let's create a toggle option here
        if (command)
        {
            def device = devices.find { it.id == params.id }
            if (!device) {
                httpError(404, "Device not found")
            } else {
                if(command == "toggle")
                {
                    if(device.currentValue('switch') == "on")
                      device.off();
                    else
                      device.on();
                }
                else if(command == "level")
                {
                    device.setLevel(level.toInteger())
                }
                else if(command == "events")
                {
                    device.events(max: 20)
                }

                else
                {
                    device."$command"()
                }
            }
        }
    }

    private item(device, s) {
        device && s ? [uid: s.id, device_id: device.id, label: device.displayName, name: s.name, value: s.value, date: s.date] : null
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
