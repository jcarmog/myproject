/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
var app = {
    // Application Constructor
    initialize: function() {
        document.addEventListener(
            "deviceready",
            this.onDeviceReady.bind(this),
            false
        );
    },

    // deviceready Event Handler
    //
    // Bind any cordova events here. Common events are:
    // 'pause', 'resume', etc.
    onDeviceReady: function() {
        this.receivedEvent("deviceready");
        this.batteryInfo();
        this.geolocationInfo();
        this.carregarBotoes();
        this.watchPosition();
    },

    // Update DOM on a Received Event
    receivedEvent: function(id) {
        parentElement = document.getElementById(
            id
        );
        /*
           var listeningElement = parentElement.querySelector(".listening");
           var receivedElement = parentElement.querySelector(".received");*/
        var deviceElement = document.getElementById("device");

        deviceElement.innerHTML = "Cordova: " + device.cordova;
        deviceElement.innerHTML += "<br/>Fabricante: " + device.manufacturer;
        deviceElement.innerHTML += "<br/>Modelo: " + device.model;
        deviceElement.innerHTML += "<br/>Plataforma: " + device.platform;
        deviceElement.innerHTML += "<br/>UUID: " + device.uuid;
        deviceElement.innerHTML += "<br/>Versão: " + device.version;
        /*
    listeningElement.setAttribute("style", "display:none;");
    receivedElement.setAttribute("style", "display:block;");
*/
        console.log("Received Event: " + id);
    },

    batteryInfo: function() {
        var batteryStatus = document.getElementById("batteryStatus");
        window.addEventListener(
            "batterystatus",
            function(status) {
                batteryStatus.innerHTML = "Plugado: " + status.isPlugged;
                batteryStatus.innerHTML += "<br/>Level: " + status.level;
            },
            false
        );
    },
    geolocationInfo: function() {
        var geolocationInfo = document.getElementById("geolocationInfo");
        var onSuccess = function(position) {
            geolocationInfo.innerHTML = "Latitude: " + position.coords.latitude;
            geolocationInfo.innerHTML +=
                "<br/>Longitude: " + position.coords.longitude;
            geolocationInfo.innerHTML += "<br/>Altitude: " + position.coords.altitude;
            geolocationInfo.innerHTML += "<br/>Accuracy: " + position.coords.accuracy;
            geolocationInfo.innerHTML +=
                "<br/>Altitude Accuracy: " + position.coords.altitudeAccuracy;
            geolocationInfo.innerHTML += "<br/>Heading: " + position.coords.heading;
            geolocationInfo.innerHTML += "<br/>Speed: " + position.coords.speed;
            geolocationInfo.innerHTML +=
                "<br/>Timestamp: " + getDateFromTimestamp(position.timestamp);
        };

        function onError(error) {
            geolocationInfo.innerHTML = "code: " + error.code;
            geolocationInfo.innerHTML += "<br/>message: " + error.message;
        }
        navigator.geolocation.getCurrentPosition(onSuccess, onError);
    },
    carregarBotoes: function() {
        var btnAlert = document.getElementById("btnAlert");
        var btnConfirm = document.getElementById("btnConfirm");
        var btnPrompt = document.getElementById("btnPrompt");
        var btnBeep = document.getElementById("btnBeep");
        btnAlert.addEventListener(
            "click",
            function() {
                navigator.notification.alert(
                    "You are the winner!", // message
                    function() {
                        console.log("Alert fechado");
                    }, // callback
                    "Game Over", // title
                    "Done" // buttonName
                );
            },
            false
        );
        btnConfirm.addEventListener(
            "click",
            function() {
                navigator.notification.confirm(
                    "You are the winner!", // message
                    onConfirm, // callback to invoke with index of button pressed
                    "Game Over", // title
                    ["Restart", "Exit"] // buttonLabels
                );

                function onConfirm(buttonIndex) {
                    alert("You selected button " + buttonIndex);
                }
            },
            false
        );
        btnPrompt.addEventListener(
            "click",
            function() {
                navigator.notification.prompt(
                    "Please enter your name", // message
                    onPrompt, // callback to invoke
                    "Registration", // title
                    ["Ok", "Exit"], // buttonLabels
                    "Jane Doe" // defaultText
                );

                function onPrompt(results) {
                    alert(
                        "You selected button number " +
                        results.buttonIndex +
                        " and entered " +
                        results.input1
                    );
                }
            },
            false
        );
        btnBeep.addEventListener(
            "click",
            function() {
                navigator.notification.beep(2);
            },
            false
        );
    },
    watchPosition: function() {
        function onSuccess(position) {
            var element = document.getElementById("geolocationInfoChanged");
            element.innerHTML =
                "Latitude: " +
                position.coords.latitude +
                "<br />" +
                "Longitude: " +
                position.coords.longitude +
                "<br />" +
                "<hr />" +
                element.innerHTML;
            $("#pnlGeolocationChanges").trigger("updatelayout");

        }

        // onError Callback receives a PositionError object
        //
        function onError(error) {
            alert("code: " + error.code + "\n" + "message: " + error.message + "\n");
        }

        // Options: throw an error if no update is received every 30 seconds.
        //
        var watchID = navigator.geolocation.watchPosition(onSuccess, onError, {
            timeout: 30000
        });
    }
};

function getDateFromTimestamp(valor) {
    var a = new Date(valor);
    var months = [
        "Jan",
        "Feb",
        "Mar",
        "Apr",
        "May",
        "Jun",
        "Jul",
        "Aug",
        "Sep",
        "Oct",
        "Nov",
        "Dec"
    ];
    var year = a.getFullYear();
    var month = months[a.getMonth()];
    var date = a.getDate();
    var hour = a.getHours();
    var min = a.getMinutes() < 10 ? "0" + a.getMinutes() : a.getMinutes();
    var sec = a.getSeconds() < 10 ? "0" + a.getSeconds() : a.getSeconds();
    var sec = a.getSeconds();
    var time =
        date + " " + month + " " + year + " " + hour + ":" + min + ":" + sec;
    return time;
}
app.initialize();