<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <link rel="apple-touch-icon" sizes="76x76" href="programmerVisite/assets/img/apple-icon.png"/>
    <link rel="icon" type="image/png" href="programmerVisite/assets/img/favicon.png"/>
    <title>HCH - immobilier </title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport'/>
    <meta name="viewport" content="width=device-width"/>

    <!-- CSS Files -->
    <link href="programmerVisite/assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="programmerVisite/assets/css/paper-bootstrap-wizard.css" rel="stylesheet"/>
    <link href="programmerVisite/assets/css/bootstrap-slider.css" rel="stylesheet"/>

    <!-- CSS Just for demo purpose, don't include it in your project -->
    <!--<link href="programmerVisite/assets/css/demo.css" rel="stylesheet"/>-->

    <!-- Fonts and Icons -->
    <link href="http://netdna.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <link href="programmerVisite/assets/css/themify-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.10/css/all.css"
          integrity="sha384-+d0P83n9kaQMCwj8F4RJB66tzIwOKmrdb46+porD/OvrJ+37WqIM7UoBtwHO6Nlg" crossorigin="anonymous">


    <style>

        .slider-handle {
            background: #68B3C8;
        }

        .slider-selection {
            background: #68B3C8;
        }
    </style>

    <!-- fullCalendar -->
    <link rel="stylesheet" href="./programmerVisite/assets/fullcalendar/dist/fullcalendar.min.css">
    <link rel="stylesheet" href="./programmerVisite/assets/fullcalendar/dist/fullcalendar.print.min.css" media="print">
    <!-- DataTables -->
    <link rel="stylesheet" href="./programmerVisite/assets/datatables.net-bs/css/dataTables.bootstrap.min.css">
    <link rel="stylesheet" href="./programmerVisite/assets/datatables-select/select.dataTables.min.css">
    <link rel="stylesheet" href="./programmerVisite/assets/datatables-responsive/responsive.dataTables.min.css">
    <!--<link rel="stylesheet" href="https://bootswatch.com/4/minty/bootstrap.min.css">-->
    <style>

        #top,
        #calendar.fc-unthemed {
            font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
        }

        #calendar {
            max-width: 900px;
            margin: 40px auto;
            padding: 0 10px;
        }

        #calendar .fc-bg {
            /*background: #e0f7fa;*/
        }

        #calendar .fc-bgevent {
            background: red;
        }

        #calendar .fc-highlight {
            background: #009688;
        }
    </style>


</head>

<body>
<div class="image-container set-full-height" style="background:#cfd8dc">

    <!--   Big container   -->
    <div class="container">
        <div class="row">
            <div class="col-sm-8 col-sm-offset-2">
                <!--      Wizard container        -->
                <div class="wizard-container">
                    <div class="card wizard-card" data-color="orange" id="wizard">
                        <form action="" method="">
                            <!--        You can switch " data-color="green" "  with one of the next bright colors: "blue", "azure", "orange", "red"       -->

                            <div class="wizard-header">
                                <h3 class="wizard-title">Modifier la date d'une visite</h3>
                                <p class="category">Suivez les etapes pour modifier une visite.</p>
                            </div>
                            <div class="wizard-navigation">
                                <div class="progress-with-circle">
                                    <div class="progress-bar" role="progressbar" aria-valuenow="1" aria-valuemin="1"
                                         aria-valuemax="4" style="width: 15%;"></div>
                                </div>
                                <ul>
                                    <li>
                                        <a href="#date" data-toggle="tab">
                                            <div class="icon-circle">
                                                <i class="ti-calendar"></i>
                                            </div>
                                            Date rdv
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#description" data-toggle="tab">
                                            <div class="icon-circle">
                                                <i class="ti-list"></i>
                                            </div>
                                            Details
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            <div class="tab-content">
                                <div class="tab-pane" id="date">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <div id='calendar'></div>
                                            <input type="hidden" name="regionId" id="regionId"
                                                   value="<%out.print(request.getParameter("regionId"));%>">
                                            <input type="hidden" name="clientId" id="clientId"
                                                   value="<%out.print(request.getParameter("clientId"));%>">
                                            <input type="hidden" name="logementId" id="logementId"
                                                   value="<%out.print(request.getParameter("logementId"));%>">
                                            <input type="hidden" name="visiteId" id="visiteId"
                                                   value="<%out.print(request.getParameter("visiteId"));%>">

                                            <input type="hidden" name="rdv" id="idAgent">
                                            <input type="hidden" name="rdv" id="heureDebutVisite">
                                            <input type="hidden" name="rdv" id="heureFinVisite">
                                        </div>
                                    </div>
                                </div>
                                <div class="tab-pane" id="description">


                                    <div class="col-xs-12 col-md-12 ">
                                        <div class="panel panel-danger height">
                                            <div class="panel-heading">Details de la visite</div>
                                            <div class="panel-body">
                                                <div class=" col-md-4 ">
                                                    <strong>Agent ID:</strong>
                                                    <span id="idAgentDetails"></span>
                                                    <br>
                                                    <strong>Date:</strong>
                                                    <span id="dateVisiteDetails"></span>
                                                    <br>
                                                </div>
                                                <div class="col-md-4 ">
                                                    <strong>Nom Agent:</strong>
                                                    <span id="nomAgentDetails"></span>
                                                    <br>
                                                    <strong>Heure:</strong>
                                                    <span id="heureDetails"></span>
                                                    <br>
                                                </div>
                                                <div class="col-md-4 ">
                                                    <strong>telephone:</strong>
                                                    <span id="tellAgentDetails"></span>
                                                    <br>
                                                    <strong>Durée:</strong>
                                                    <span id="dureeVisiteDetails"></span>
                                                    <br>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="wizard-footer">
                                <div class="pull-right">
                                    <input type='button' class='btn btn-next btn-fill btn-success btn-wd'
                                           name='next'
                                           value='Next'/>
                                    <input type='button' class='btn btn-finish btn-fill btn-success btn-wd'
                                           name='finish' value='Finish' onclick="confirmerVisite()"/>
                                </div>

                                <div class="pull-left">
                                    <input type='button' class='btn btn-previous btn-default btn-wd'
                                           name='previous'
                                           value='Previous'/>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </form>
                    </div>
                </div> <!-- wizard container -->
            </div>
        </div> <!-- row -->
    </div> <!--  big container -->
</div>
</body>

<!--   Core JS Files   -->
<script src="programmerVisite/assets/js/jquery-2.2.4.min.js" type="text/javascript"></script>
<script src="programmerVisite/assets/js/bootstrap.min.js" type="text/javascript"></script>
<script src="programmerVisite/assets/js/jquery.bootstrap.wizard.js" type="text/javascript"></script>
<script src="programmerVisite/assets/js/bootstrap-slider.js" type="text/javascript"></script>

<!--  Plugin for the Wizard -->
<!--<script src="programmerVisite/assets/js/paper-bootstrap-wizard.js" type="text/javascript"></script>-->

<!--  More information about jquery.validate here: http://jqueryvalidation.org/	 -->
<script src="programmerVisite/assets/js/jquery.validate.min.js" type="text/javascript"></script>


<!-- fullCalendar -->
<script src="./programmerVisite/assets/moment/moment.js"></script>
<script src="./programmerVisite/assets/fullcalendar/dist/fullcalendar.min.js"></script>
<script src="./programmerVisite/assets/fullcalendar/dist/locale/fr.js"></script>

<!-- DataTables -->
<script src="./programmerVisite/assets/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="./programmerVisite/assets/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<script src="./programmerVisite/assets/datatables-select/dataTables.select.min.js"></script>
<script src="./programmerVisite/assets/datatables-responsive/dataTables.responsive.min.js"></script>
<script src="./programmerVisite/assets/js/modifierVisite-main.js"></script>


</html>
