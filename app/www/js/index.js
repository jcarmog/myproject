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
        console.log(navigator.vibrate);
        window.addEventListener('filePluginIsReady', function() { console.log('File plugin is ready'); }, false);
        this.receivedEvent("deviceready");
        this.batteryInfo();
        this.geolocationInfo();
        this.networkInfo();
        this.carregarBotoes();
        this.watchPosition();
        this.vibration();
        this.screenOrientation();
    },

    // Update DOM on a Received Event
    receivedEvent: function(id) {
        StatusBar.backgroundColorByHexString("#C0C0C0");
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
        var btnAbrirCamera = document.getElementById("btnAbrirCamera");
        var btnExibirArquivos = document.getElementById("btnExibirArquivos");
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
        btnAbrirCamera.addEventListener("click", function() {
            navigator.camera.getPicture(function(imageData) {
                var image = document.getElementById('myimage');
                image.src = imageData;
            }, function(message) {
                alert('Failed because: ' + message);
            }, { quality: 50, targetWidth: 400, targetHeight: 400, allowEdit: true, correctOrientation: false });

        }, false);
        btnExibirArquivos.addEventListener("click", function() {
            var lstArquivos = document.getElementById("lstArquivos");

            var myPath = cordova.file.applicationDirectory; // We can use the default externalRootDirectory or use a path : file://my/custom/folder
            alert(myPath);
            window.resolveLocalFileSystemURL(myPath, function(dirEntry) {
                var directoryReader = dirEntry.createReader();
                directoryReader.readEntries(onSuccessCallback, onFailCallback);
            });

            function onSuccessCallback(entries) {
                //alert(entries);
                var html;
                for (i = 0; i < entries.length; i++) {
                    var row = entries[i];

                    //alert(row.nativeURL + " - " + row.name);
                    if (row.isDirectory) {
                        // We will draw the content of the clicked folder
                        html += '<li data-icon="shop" >++ ' + row.name + '</li>\n';
                    } else {
                        // alert the path of file
                        html += '<li data-icon="bars" >-' + row.name + '</li>\n';
                    }

                }
                //alert(html);
                lstArquivos.innerHTML = html;
                // $("#lstArquivos").trigger("updatelayout");
            }

            function onFailCallback() {
                alert("erro======");
            }

        }, false);
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
                "<br />" +
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
    },
    vibration: function() {
        var btnVibrar = document.getElementById("btnVibrar");
        var btnVibrarPadrao = document.getElementById("btnVibrarPadrao");
        var btnVibrarParar = document.getElementById("btnVibrarParar");
        btnVibrar.addEventListener("click", function() {
            navigator.vibrate(3000);
        }, false);
        btnVibrarPadrao.addEventListener("click", function() {
            navigator.vibrate([1000, 1000, 3000, 1000, 5000]);
        }, false);
        btnVibrarParar.addEventListener("click", function() {
            navigator.vibrate(0);
        }, false);
    },
    networkInfo: function() {
        updateNetworkStatus();
        document.addEventListener("online", updateNetworkStatus, false);
        document.addEventListener("offline", updateNetworkStatus, false);

        function updateNetworkStatus() {
            var networkState = navigator.connection.type;
            var states = {};
            states[Connection.UNKNOWN] = 'Unknown connection';
            states[Connection.ETHERNET] = 'Ethernet connection';
            states[Connection.WIFI] = 'WiFi connection';
            states[Connection.CELL_2G] = 'Cell 2G connection';
            states[Connection.CELL_3G] = 'Cell 3G connection';
            states[Connection.CELL_4G] = 'Cell 4G connection';
            states[Connection.CELL] = 'Cell generic connection';
            states[Connection.NONE] = 'No network connection';
            if (networkState !== Connection.NONE) {
                document.getElementById("networkInfo").innerHTML = states[networkState];
            } else {
                document.getElementById("networkInfo").innerHTML = "Você está offline";
            }
        }
    },
    screenOrientation: function() {
        document.getElementById("screenOrientation").innerHTML = screen.orientation.type;
        //screen.orientation.lock('portrait');

        // allow user rotate
        screen.orientation.unlock();
        window.addEventListener("orientationchange", function() {
            document.getElementById("screenOrientation").innerHTML = screen.orientation.type;
            console.log(screen.orientation.type); // e.g. portrait
        });
    }

};

function getDateFromTimestamp(valor) {
    var a = new Date(valor);
    var months = [
        "Jan",
        "Feb",
        "Mar",
        "Abr",
        "Mai",
        "Jun",
        "Jul",
        "Ago",
        "Set",
        "Out",
        "Nov",
        "Dez"
    ];
    var year = a.getFullYear();
    var month = months[a.getMonth()];
    var date = a.getDate();
    var hour = a.getHours();
    var min = a.getMinutes() < 10 ? "0" + a.getMinutes() : a.getMinutes();
    var sec = a.getSeconds() < 10 ? "0" + a.getSeconds() : a.getSeconds();
    var time =
        date + " " + month + " " + year + " " + hour + ":" + min + ":" + sec;
    return time;
}
app.initialize();