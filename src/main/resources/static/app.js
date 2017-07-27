var Marker = new Array();
var data_marker = new Array();
function initMap() {
    var i;
    var index_marker;
    var jml_marker_install;
    var jml_marker_verify;
    var jml_marker_login;
    var jml_marker_vote;
    var check_marker = 0;
    var pens = {lat: -7.2802954, lng: 112.794229};
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 15,
        center: pens
    });
    var stompClient = null;

    $.get("/monitoring", function (data, status) {
        data_marker = data;
        for(i=0;i<data.length;i++){
           switch (Number(data[i].status)){
               case 1:
                   Marker[index_marker] = new google.maps.Marker({
                       position: {lat:data[i].latitude, lng:data[i].longitude},
                       map: map,
                       icon:'http://maps.google.com/mapfiles/ms/icons/yellow-dot.png'
                   });
                   break;
               case 2:
                   Marker[index_marker] = new google.maps.Marker({
                       position: {lat:data[i].latitude, lng:data[i].longitude},
                       map: map,
                       icon:'http://maps.google.com/mapfiles/ms/icons/blue-dot.png'
                   });
                   break;
               case 3:
                   Marker[index_marker] = new google.maps.Marker({
                       position: {lat:data[i].latitude, lng:data[i].longitude},
                       map: map,
                       icon:'http://maps.google.com/mapfiles/ms/icons/green-dot.png'
                   });
                   break;
           }
        }
        check_device();
    });


    var socket = new SockJS('/device');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/server/data_voter', function (voter) {
            var data_voter = JSON.parse(voter.body);
            for(i=0;i<data_marker.length;i++){
                if(data_marker[i].uuid == data_voter.uuid){
                    console.log(data_marker[i].uuid);
                    check_marker = 1;
                    index_marker = i;
                    break;
                }
            }

            if(check_marker == 1){
                data_marker[index_marker] = data_voter;
                Marker[index_marker].setMap(null);
                switch (Number(data_voter.status)){
                    case 2:
                        Marker[index_marker] = new google.maps.Marker({
                            position: {lat:data_voter.latitude, lng:data_voter.longitude},
                            map: map,
                            icon:'http://maps.google.com/mapfiles/ms/icons/blue-dot.png'
                        });
                        break;
                    case 3:
                        Marker[index_marker] = new google.maps.Marker({
                            position: {lat:data_voter.latitude, lng:data_voter.longitude},
                            map: map,
                            icon:'http://maps.google.com/mapfiles/ms/icons/green-dot.png'
                        });
                        break;
                }
            }else{
                data_marker.push(data_voter);
                Marker[data_marker.length-1] = new google.maps.Marker({
                    position: {lat:data_voter.latitude, lng:data_voter.longitude},
                    map: map,
                    icon:'http://maps.google.com/mapfiles/ms/icons/yellow-dot.png'
                });
            }
            check_device();
            check_marker = 0;
        });
    });
}
function check_device() {
    jml_marker_install = 0;
    jml_marker_verify = 0;
    jml_marker_login = 0;
    jml_marker_vote = 0;
    for(i=0;i<data_marker.length;i++){
        switch (Number(data_marker[i].status)){
            case 0:
                jml_marker_install++;
                break;
            case 1:
                jml_marker_verify++;
                break;
            case 2:
                jml_marker_login++;
                break;
            case 3:
                jml_marker_vote++;
                break;
        }
    }
    document.getElementById("terverifikasi").innerHTML = jml_marker_verify;
    document.getElementById("login").innerHTML = jml_marker_login;
    document.getElementById("vote").innerHTML = jml_marker_vote;
}