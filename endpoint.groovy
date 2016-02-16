/**
 *  App Endpoint API Access Example
 *
 *  Author: SmartThings - Jody Albritton
 */



    import groovy.json.JsonBuilder
    definition(
        name: "API Endpoint App",
        namespace: "jodyalbritton",
        author: "Jody Albritton",
        description: "SmartApp API Endpoint ",
        category: "My Apps",
        iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
        iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
        oauth: [displayName: "API ENDPOINT", displayLink: ""])

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


        path("/switches") {
            action: [
                GET: "listSwitches"
            ]
        }
        path("/switches/:id") {
            action: [
                GET: "showSwitch"
            ]
        }
        path("/switches/:id/:command") {
            action: [
                POST: "updateSwitch"
            ]
        }
        path("/switches/:id/:events") {
            action: [
                GET: "showSwitchEvents"
            ]
        }

        path("/locks") {
            action: [
                GET: "listLocks"
            ]
        }
        path("/locks/:id") {
            action: [
                GET: "showLock"
            ]
        }
        path("/locks/:id/:command") {
            action: [
                POST: "updateLock"
            ]
        }


        path("/dimmers") {
            action: [
                GET: "listDimmers"
            ]
        }
        path("/dimmers/:id") {
            action: [
                GET: "showDimmer"
            ]
        }
        path("/dimmers/:id/:command") {
            action: [
                POST: "updateDimmer"
            ]
        }
        path("/switches/:id/:command/:level") {
            action: [
                POST: "updateSwitch"
            ]
        }
        path("/motions") {
            action: [
                GET: "listMotions"
            ]
        }
        path("/motions/:id") {
            action: [
                GET: "showMotion"
            ]
        }
         path("/motions/:id/events") {
            action: [
                GET: "showMotionEvents"
            ]
        }
         path("/illuminants") {
            action: [
                GET: "listIlluminants"
            ]
        }
        path("/illuminants/:id") {
            action: [
                GET: "showIlluminant"
            ]
        }
         path("/contacts") {
            action: [
                GET: "listContacts"
            ]
        }
        path("/contacts/:id") {
            action: [
                GET: "showContact"
            ]
        }
        path("/temperatures") {
            action: [
                GET: "listTemperatures"
            ]
        }
        path("/temperatures/:id") {
            action: [
                GET: "showTemperature"
            ]
        }
        path("/temperatures/:id/:command") {
            action: [
                POST: "updateTemperatures"
            ]
        }
        path("/humidities") {
            action: [
                GET: "listHumidities"
            ]
        }
        path("/humidities/:id") {
            action: [
                GET: "showHumidity"
            ]
        }

        path("/batteries") {
            action: [
                GET: "listBatteries"
            ]
        }
        path("/batteries/:id") {
            action: [
                GET: "showBattery"
            ]
        }

        path("/powers") {
            action: [
                GET: "listPowers"
            ]
        }
        path("/powers/:id") {
            action: [
                GET: "showPower"
            ]
        }
        path("/energies") {
            action: [
                GET: "listEnergies"
            ]
        }
        path("/energies/:id") {
            action: [
                GET: "showEnergy"
            ]
        }
        path("/thermostats") {
            action: [
                GET: "listThermostats"
            ]
        }
        path("/thermostats/:id") {
            action: [
                GET: "showThermostat"
            ]
        }  

        path("/thermostats/:id/:command/:temp") {
            action: [
                POST: "updateThermostat"
            ]
        }

        path("/presence") {
            action: [
                GET: "listPresence"
            ]
        }  

        path("/presences/:id") {
            action: [
                GET: "showPresence"
            ]
        }     
    }




    /*********************************************************************
    * SWITCHES 
    * 
    * 
    **********************************************************************/
    
    def listSwitches() {
        switches: switches?.collect{[type: "switch", id: it.id, name: it.displayName, status: it.currentValue('switch')]}?.sort{it.name}
    }


    def showSwitch() {
        show(switches, "switch")

    }

    def showSwitchEvents() {
        getEvents(switches, "switch")
    }


    void updateSwitch() {
        update(switches)
    }



    /*********************************************************************
    * DIMMERS
    * 
    * 
    **********************************************************************/
    def listDimmers() {
        dimmers?.collect{[type: "dimmer", id: it.id, name: it.displayName, level: it.currentValue('level')]}?.sort{it.name}
    }
    def showDimmer() {
        show(dimmers, "level")
    }
    void updateDimmer() {
        update(dimmers)
    }

    /*********************************************************************
    * LOCKS 
    * 
    * 
    **********************************************************************/

    def listLocks() {
        lock?.collect{[type: "lock", id: it.id, name: it.displayName, status: it.currentValue('lock')]}?.sort{it.name}

    }
    def showLock() {
        show(lock, "lock")
    }
    void updateLock() {
        update(lock)
    }



    /*********************************************************************
    * TEMPERATURES 
    * 
    * 
    **********************************************************************/
    def listTemperatures() {
        temperatures?.collect{[type: "temperatureMeasurement", id: it.id, name: it.displayName, status: it.currentValue('temperature')]}?.sort{it.name}
    }

    def showTemperature() {
        show(temperatures, "temperature")
    }

    /*********************************************************************
    * HUMIDITIES 
    * 
    * 
    **********************************************************************/
    def listHumidities() {
        humidities?.collect{[type: "relativeHumidityMeasurement", id: it.id, name: it.displayName, status: it.currentValue('humidity')]}?.sort{it.name}
    }

    def showHumidity() {
        show(humidities, "humidity")
    }

    /*********************************************************************
    * PRESENCES 
    * 
    * 
    **********************************************************************/
    def listPresences() {
        presences?.collect{[type: "presence", id: it.id, name: it.displayName, status: it.currentValue('presence')]}?.sort{it.name}

    }
    def showPresence() {
        show(presences, "presence")
    }


    /*********************************************************************
    * MOTIONS 
    * 
    * 
    **********************************************************************/
    def listMotions() {
         motions?.collect{[type: "motion", id: it.id, name: it.displayName, status: it.currentValue('motion')]}?.sort{it.name}

    }
    def showMotion() {
        show(motions, "motion")

    }

    def showMotionEvents() {
        getEvents(motions, "motion")
    }


    /*********************************************************************
    * ILLUMINANTS 
    * 
    * 
    **********************************************************************/
    def listIlluminants() {
         illuminants?.collect{[type: "illuminant", id: it.id, name: it.displayName, status: it.currentValue('illuminance')]}?.sort{it.name}

    }
    def showIlluminant() {
        show(illuminants, "illuminance")
    }

    /*********************************************************************
    * CONTACT SENSORS 
    * 
    * 
    **********************************************************************/
    def listContacts() {
         contacts?.collect{[type: "contact", id: it.id, name: it.displayName, status: it.currentValue('contact')]}?.sort{it.name}

    }
    def showContact() {
        show(contacts, "contact")
    }


    /*********************************************************************
    * BATTERIES 
    * 
    * 
    **********************************************************************/
    def listBatteries() {
         batteries?.collect{[type: "battery", id: it.id, name: it.displayName, status: it.currentValue('battery')]}?.sort{it.name}

    }
    def showBattery() {
        show(batteries, "battery")
    }


    /*********************************************************************
    * POWERS 
    * 
    * 
    **********************************************************************/
    def listPowers() {
         powers?.collect{[type: "power", id: it.id, name: it.displayName, status: it.currentValue("power")]}?.sort{it.name}

    }
    def showPower() {
        show(powers, "power")
    }

    /*********************************************************************
    * ENERGYS
    * 
    * 
    **********************************************************************/
    def listEnergies() {
         energys?.collect{[type: "energy", id: it.id, name: it.displayName, status: it.currentValue("energy")]}?.sort{it.name}

    }
    def showEnergy() {
        show(energys, "energy")
    }


    /*********************************************************************
    * THERMOSTATS 
    * 
    * 
    **********************************************************************/
    def listThermostats() {
        thermostats.collect{device(it,"thermostat")}
    }

    def showThermostat() {
        show(thermostats, "thermostat")
    }

    void updateThermostat() {

        def device = thermostats.find { it.id == params.id }
        def command = params.command
        def temp = params.temp

        log.debug "$command ${params.id} at $temp"

        if(command == 'heat')
        {
            device.setHeatingSetpoint(temp)
        }
        else if(command == 'cool')
        {
          device.setCoolingSetpoint(temp)	
        }
    }

    def deviceHandler(evt) {}

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

    private show(devices, type) {
        def device = devices.find { it.id == params.id }
        if (!device) {
            httpError(404, "Device not found")
        }
        else {
            def attributeName = type == "motionSensor" ? "motion" : type
            def s = device.currentState(attributeName)
            [id: device.id, label: device.displayName, value: s?.value, unitTime: s?.date?.time, type: type]
        }
    }

    private getEvents(devices, type) {
        def device = devices.find { it.id == params.id }
        if (!device) {
            httpError(404, "Device not found")
        }
        else {
            def events = device.events(max: 40)
             events = events.findAll{it.name == type}
            def result = events.collect{item(device, it)}
            result
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