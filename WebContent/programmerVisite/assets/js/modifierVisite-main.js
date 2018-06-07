/*!

 =========================================================
 * Paper Bootstrap Wizard - v1.0.2
 =========================================================

 * Product Page: https://www.creative-tim.com/product/paper-bootstrap-wizard
 * Copyright 2017 Creative Tim (http://www.creative-tim.com)
 * Licensed under MIT (https://github.com/creativetimofficial/paper-bootstrap-wizard/blob/master/LICENSE.md)

 =========================================================

 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 */

// Paper Bootstrap Wizard Functions

searchVisible = 0;
transparent = true;


$(document).ready(function () {


    /*  Activate the tooltips      */
    $('[rel="tooltip"]').tooltip();

    // Code for the Validator
    var $validator = $('.wizard-card form').validate({
        rules: {
            nbrPieces: {
                required: true
            },
            nbrSdb: {
                required: true
            },
            nbrEtages: {
                required: true
            },
            region: {
                required: true
            }
        }
    });

    // Wizard Initialization
    $('.wizard-card').bootstrapWizard({
        'tabClass': 'nav nav-pills',
        'nextSelector': '.btn-next',
        'previousSelector': '.btn-previous',

        onNext: function (tab, navigation, index) {
            var $valid = $('.wizard-card form').valid();
            if (!$valid) {
                $validator.focusInvalid();
                return false;
            }

            if (index === 2)
                initLogementsTable();
            if (index === 5)
                fillDetails();

        },

        onInit: function (tab, navigation, index) {

            //check number of tabs and fill the entire row
            var $total = navigation.find('li').length;
            $width = 100 / $total;

            navigation.find('li').css('width', $width + '%');

        },

        onTabClick: function (tab, navigation, index) {
            fillDetails();
            if (index === 2) initLogementsTable();

            return $('.wizard-card form').valid();

        },

        onTabShow: function (tab, navigation, index) {
            var $total = navigation.find('li').length;
            var $current = index + 1;

            var $wizard = navigation.closest('.wizard-card');

            // If it's the last tab then hide the last button and show the finish instead
            if ($current >= $total) {
                $($wizard).find('.btn-next').hide();
                $($wizard).find('.btn-finish').show();
            } else {
                $($wizard).find('.btn-next').show();
                $($wizard).find('.btn-finish').hide();
            }

            //update progress
            var move_distance = 100 / $total;
            move_distance = move_distance * (index) + move_distance / 2;

            $wizard.find($('.progress-bar')).css({width: move_distance + '%'});
            //e.relatedTarget // previous tab

            $wizard.find($('.wizard-card .nav-pills li.active a .icon-circle')).addClass('checked');

        }
    });


    // Prepare the preview for profile picture
    $("#wizard-picture").change(function () {
        readURL(this);
    });

    $('[data-toggle="wizard-radio"]').click(function () {
        wizard = $(this).closest('.wizard-card');
        wizard.find('[data-toggle="wizard-radio"]').removeClass('active');
        $(this).addClass('active');
        $(wizard).find('[type="radio"]').removeAttr('checked');
        $(this).find('[type="radio"]').attr('checked', 'true');
    });

    $('[data-toggle="wizard-checkbox"]').click(function () {
        if ($(this).hasClass('active')) {
            $(this).removeClass('active');
            $(this).find('[type="checkbox"]').removeAttr('checked');
        } else {
            $(this).addClass('active');
            $(this).find('[type="checkbox"]').attr('checked', 'true');
        }
    });

    $('.set-full-height').css('height', 'auto');

});

//Function to show image before upload

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#wizardPicturePreview').attr('src', e.target.result).fadeIn('slow');
        };
        reader.readAsDataURL(input.files[0]);
    }
}

$("#prix").slider({});
$("#superficie").slider({});

var myType = "villa";
if ($('#appartement').checked) myType = "appartement";
var region = $('#maregion option:selected').val();
var pricee = $('#prix').val();
var superficie = $('#superficie').val();
var nbrPieces = $('#nbrPieces').val();
var nbrSdb = $('#nbrSdb').val();
var nbrEtages = $('#nbrEtages').val();

var meuble = "false";
var garage = "false";
var jardin = "false";
var soussol = "false";

if ($('#soussol').is(":checked")) soussol = "true";
if ($('#jardin').is(":checked")) jardin = "true";
if ($('#meuble').is(":checked")) meuble = "true";
if ($('#garage').is(":checked")) garage = "true";


var logementsTable = $('#logementsTable').DataTable({
    'paging': true,
    'lengthChange': false,
    'searching': true,
    'ordering': true,
    'info': true,
    'autoWidth': false,
    responsive: true,
    select: {
        style: 'single'
    },

    ajax: {
        url: '/api/logementApi?' +
        'action=search' +
        '&type=' + myType +
        '&region=' + region +
        '&prix=' + pricee +
        '&superficie=' + superficie +
        '&nbrPieces=' + nbrPieces +
        '&nbrSdb=' + nbrSdb +
        '&nbrEtages=' + nbrEtages +
        '&meuble=' + meuble +
        '&garage=' + garage +
        '&jardin=' + jardin +
        '&soussol=' + soussol,
        dataSrc: ''
    },
    columns: [
        {"data": "id"},
        {"data": "titre"},
        {"data": "price"},
        {"data": "superficie"},
        {"data": "adresse"},
        {"data": "description"}
    ]
});

logementsTable.on('select', function (e, dt, type, indexes) {

    var rowData = logementsTable.rows(indexes).data().toArray();

    $('#selectedlogementId').val(rowData[0]["id"]);
    $('#selectedlogementadresse').val(rowData[0]["adresse"]);
    $('#selectedLogementSuperficie').val(rowData[0]["superficie"]);
    $('#selectedlogementprice').val(rowData[0]["price"]);

    document.getElementById("idLogementDetails").innerHTML = rowData[0]["id"];
    document.getElementById("superficieDetails").innerHTML = rowData[0]["superficie"];
    document.getElementById("prixDetails").innerHTML = rowData[0]["price"];
    fillDetails();
    initCalendar(rowData[0]["id"]);
});

function initLogementsTable() {

    myType = "villa";

    if ($('#appartement').checked)
        myType = "appartement";

    region = $('#maregion option:selected').val();
    pricee = $('#prix').val();
    superficie = $('#superficie').val();
    nbrPieces = $('#nbrPieces').val();
    nbrSdb = $('#nbrSdb').val();
    nbrEtages = $('#nbrEtages').val();
    if ($('#soussol').is(":checked")) soussol = "true"; else soussol = "false";
    if ($('#jardin').is(":checked")) jardin = "true"; else jardin = "false";
    if ($('#meuble').is(":checked")) meuble = "true"; else meuble = "false";
    if ($('#garage').is(":checked")) garage = "true"; else garage = "false";


    logementsTable.ajax.url('/api/logementApi?' +
        'action=search' +
        '&type=' + myType +
        '&region=' + region +
        '&prix=' + pricee +
        '&superficie=' + superficie +
        '&nbrPieces=' + nbrPieces +
        '&nbrSdb=' + nbrSdb +
        '&nbrEtages=' + nbrEtages +
        '&meuble=' + meuble +
        '&garage=' + garage +
        '&jardin=' + jardin +
        '&soussol=' + soussol).load();
}

$(function () {


    var table = $('#clientsTab').DataTable({
            'paging': true,
            'lengthChange': false,
            'searching': true,
            //'ordering': true,
            'info': true,
            'autoWidth': false,
            //  'aDataSort': false,
            //"order": [[ 3, "asc" ]],
            select: {
                style: 'single'
            }
            ,
            "processing": true,
            "serverSide": true,
            ajax: {
                url: '/api/clientApi?action=getAllClients',
                dataSrc: ''
            },
            columns: [
                {"data": "id"},
                {"data": "nom"},
                {"data": "prenom"},
                {"data": "telephone"},
                {"data": "dateDeNaissance"},
                {"data": "isBanned"}
            ]
        })
    ;

    table.on('select', function (e, dt, type, indexes) {
        var rowData = table.rows(indexes).data().toArray();
        $('#clientId').val(rowData[0]['id']);
        document.getElementById("clientIdDetails").innerHTML = rowData[0]['id'];
        document.getElementById("numeroTelephoneClient").innerHTML = rowData[0]['telephone'];
        document.getElementById("nomCompletClient").innerHTML = rowData[0]['nom'] + " " + rowData[0]['prenom'];
        document.getElementById("dateNaissClient").innerHTML = rowData[0]['dateDeNaissance'];
    });


});


function fillDetails() {
    document.getElementById("regionDetails").innerHTML = $('#regionId').val();
    document.getElementById("nbrPiecesDetails").innerHTML = $('#nbrPieces').val();
    document.getElementById("nbrEtagesDetails").innerHTML = $('#nbrEtages').val();
    document.getElementById("nbrSDBDetails").innerHTML = $('#nbrSdb').val();

    if ($('#villa').is(":checked"))
        document.getElementById("typeDetails").innerHTML = "villa";
    else
        document.getElementById("typeDetails").innerHTML = "appartement";

    if ($('#soussol').is(":checked"))
        document.getElementById("sousSolDetails").innerHTML = "oui";
    else
        document.getElementById("sousSolDetails").innerHTML = "non";

    if ($('#jardin').is(":checked"))
        document.getElementById("jardinDetails").innerHTML = "oui";
    else
        document.getElementById("jardinDetails").innerHTML = "non";

    if ($('#meuble').is(":checked"))
        document.getElementById("meubleDetails").innerHTML = "oui";
    else
        document.getElementById("meubleDetails").innerHTML = "non";
    if ($('#garage').is(":checked"))
        document.getElementById("garageDetails").innerHTML = "oui";
    else
        document.getElementById("garageDetails").innerHTML = "non";

}

function fillOtherInputs(startDate, endDate) {


    $.ajax({
        url: "/api/visiteApi?action=getFreeAgentForDate&date=" + startDate.format() + "&region=" + $('#regionId').val(),
        success: function (result) {
            var agent = JSON.parse(result);

            console.log(result);


            $('#idAgent').val(agent.id);


            $('#heureDebutVisite').val(startDate.format());
            $('#heureFinVisite').val(endDate.format().substring(11, 16));

            document.getElementById("idAgentDetails").innerHTML = agent.id;
            document.getElementById("nomAgentDetails").innerHTML = agent.nom + " " + agent.prenom;
            document.getElementById("tellAgentDetails").innerHTML = agent.tel;

            document.getElementById("dateVisiteDetails").innerHTML = startDate.format().substring(0, 10);
            document.getElementById("heureDetails").innerHTML = endDate.format().substring(11, 16);
            document.getElementById("dureeVisiteDetails").innerHTML = "2h";
        }
    });
}

var visites;


var calendar = $('#calendar').fullCalendar({

        themeSystem: 'standard',
        defaultView: 'agendaWeek',

        header: {
            left: 'prev,next today',
            center: 'title',
            right: ''
        },
        title: "choisissez une date",
        // defaultDate: '2018-03-12',
        weekNumbers: false,
        navLinks: false, // can click day/week names to navigate views
        editable: false,
        eventLimit: true, // allow "more" link when too many events
        hiddenDays: [6, 7], // hide Tuesdays and Thursdays
        selectable: true,
        unselectAuto: false,
        businessHours: {
            // days of week. an array of zero-based day of week integers (0=Sunday)
            dow: [0, 1, 2, 3, 4, 5], // Monday - Thursday

            start: '8:00', // a start time (10am in this example)
            end: '16:00' // an end time (6pm in this example)
        },
        // days of week. an array of zero-based day of week integers (0=Sunday)
        dow: [0, 1, 2, 3, 4, 5], // Monday - Thursday
        start: '8:00:00', // a start time (10am in this example)
        end: '16:00:00', // an end time (6pm in this example)
        minTime: '08:00:00',
        maxTime: '16:00:00',
        eventSources: [
            /*    // your event source
                {
                    url: '/api/visiteApi?action=getTakenDates&logementId=' + idLogement, // use the `url` property
                    color: 'red',    // an option!
                    textColor: 'black'  // an option!
                }*/

            // any other sources...
        ],
        eventColor: '#378006',
        displayEventTime: false,
        slotDuration: "02:00:00",
        eventClick: function (calEvent, jsEvent, view) {
            return false;
        },
        select: function (startDate, endDate) {
            fillOtherInputs(startDate, endDate);
        }
    })
;

function initCalendar(idLogement) {
    var events = {
        url: '/api/visiteApi?action=getTakenDates&logementId=' + idLogement, // use the `url` property
        color: 'red',    // an option!
        textColor: 'black'  // an option!
    };
    calendar.fullCalendar('removeEventSources');
    calendar.fullCalendar('addEventSource', events);
    calendar.fullCalendar('refetchEvents');
}

function confirmerVisite() {

    var idLogement = $('#logementId').val();
    var idClient = $('#clientId').val();
    var idAgent = document.getElementById("idAgentDetails").innerHTML;
    var heureDebut = $('#heureDebutVisite').val();
    var heureFin = $('#heureFinVisite').val();
    var idVisite = $('#visiteId').val();

    var params = {
        action: "edit",
        idLogement: idLogement,
        idClient: idClient,
        idAgent: idAgent,
        heureDebut: heureDebut,
        heureFin: heureFin,
        idVisite: idVisite
    };
    post("/ProgrammerVisite", params, "GET");
}

function ajouterVente() {
    var idLogement = $('#selectedlogementId').val();
    var idClient = $('#clientId').val();

    var params = {
        action: "add",
        idLogement: idLogement,
        idClient: idClient
    };
    post("/ajouterVente", params, "GET");
}

function post(path, params, method) {
    method = method || "post"; // Set method to post by default if not specified.
    // The rest of this code assumes you are not using a library.
    // It can be made less wordy if you use one.
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);

    for (var key in params) {
        if (params.hasOwnProperty(key)) {
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", key);
            hiddenField.setAttribute("value", params[key]);

            form.appendChild(hiddenField);
        }
    }
    document.body.appendChild(form);
    form.submit();
}

function addInputToDocument(id, value) {
    document.body.find(id).remove();

    var hiddenField = document.createElement("input");
    hiddenField.setAttribute("type", "hidden");
    hiddenField.setAttribute("id", id);
    hiddenField.setAttribute("value", value);
    document.body.appendChild(hiddenField);
}


function findGetParameter(parameterName) {
    var result = null,
        tmp = [];
    location.search
        .substr(1)
        .split("&")
        .forEach(function (item) {
            tmp = item.split("=");
            if (tmp[0] === parameterName) result = decodeURIComponent(tmp[1]);
        });

    console.log("findGetParameter  =  " + result);
    return result;
}