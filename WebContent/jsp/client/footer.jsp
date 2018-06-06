<%@ page import="control.servlets.MyServlet" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="model.beans.Logement" %>
<%@ page import="control.statistics.globales.LogementsStats" %>
<%@ page import="model.db.ContactInfosDAO" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<div class="footer-area">

    <div class=" footer">
        <div class="container">
            <div class="row">

                <div class="col-md-3 col-sm-6 wow fadeInRight animated">
                    <div class="single-footer">
                        <h4>À propos de nous </h4>
                        <div class="footer-title-line"></div>

                        <img src="../../assets_client/img/footer-logo.png" alt="" class="wow pulse" data-wow-delay="1s">
                        <p><%out.print(new ContactInfosDAO().getDescriptionSociete());%></p>
                        <ul class="footer-adress">
                            <li>
                                <i class="pe-7s-map-marker strong"> </i> <%out.print(new ContactInfosDAO().getAdresseSociete());%>
                            </li>
                            <li>
                                <i class="pe-7s-mail strong"> </i> <%out.print(new ContactInfosDAO().getEmailSociete());%>
                            </li>
                            <li><i class="pe-7s-call strong"> </i> <%out.print(new ContactInfosDAO().getTelSociete());%>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-3 col-sm-6 wow fadeInRight animated">
                    <%
                        boolean loggedIn = request.getSession().getAttribute(MyServlet.LOGGED_IN_USER) != null;
                        String link = loggedIn ? "/DashboardServlet" : "/home";
                    %>
                    <div class="single-footer">
                        <h4>Liens rapides </h4>
                        <div class="footer-title-line"></div>
                        <ul class="footer-menu">
                            <li><a href="<%out.print(link+"?what=logements");%>">Logements</a></li>
                            <li><a href="<%out.print(link + "?what=contacter");%>">Nous contacter</a></li>
                            <li><a href="faq.html">FAQ</a></li>
                            <li><a href="faq.html">Règles et conditions d'utilisation</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-3 col-sm-6 wow fadeInRight animated">
                    <div class="single-footer">
                        <h4>Nouveautés</h4>
                        <div class="footer-title-line"></div>
                        <ul class="footer-blog">
                            <%
                                LinkedList<Logement> newestLogements = new LogementsStats().dernierLogementsAjoutes();
                                for (Logement logement : newestLogements) {

                                    out.print("" +
                                            "<li>\n" +
                                            "                                <div class=\"col-md-3 col-sm-4 col-xs-4 blg-thumb p0\">\n" +
                                            "                                    <a href=\"single.html\">\n" +
                                            "                                        <img src=\"../../assets_client/img/demo/small-proerty-2.jpg\">\n" +
                                            "                                    </a>\n" +
                                            "                                    <span class=\"blg-date\">" + logement.getLocalite().getNom() + "</span>\n" +
                                            "\n" +
                                            "                                </div>\n" +
                                            "                                <div class=\"col-md-8  col-sm-8 col-xs-8  blg-entry\">\n" +
                                            "                                    <h6><a href=\"single.html\">" + logement.getTitre() + "</a></h6>\n" +
                                            "                                    <p style=\"line-height: 17px; padding: 8px 2px;\">" + logement.getDescription() + "\n" +
                                            "                                        ...</p>\n" +
                                            "                                </div>\n" +
                                            "                            </li>");
                                }
                            %>


                        </ul>
                    </div>
                </div>
                <div class="col-md-3 col-sm-6 wow fadeInRight animated">
                    <div class="single-footer news-letter">
                        <h4>Suivez nous partout</h4>
                        <div class="footer-title-line"></div>
                        <p>Rejoignez nous sur les réseaux sociaux !</p>

                        <div class="social pull-right">
                            <ul>
                                <li><a class="wow fadeInUp animated" href="https://twitter.com/"><i
                                        class="fa fa-twitter"></i></a></li>
                                <li><a class="wow fadeInUp animated" href="https://www.facebook.com/"
                                       data-wow-delay="0.2s"><i class="fa fa-facebook"></i></a></li>
                                <li><a class="wow fadeInUp animated" href="https://plus.google.com/"
                                       data-wow-delay="0.3s"><i class="fa fa-google-plus"></i></a></li>
                                <li><a class="wow fadeInUp animated" href="https://instagram.com/"
                                       data-wow-delay="0.4s"><i class="fa fa-instagram"></i></a></li>
                                <li><a class="wow fadeInUp animated" href="https://instagram.com/"
                                       data-wow-delay="0.6s"><i class="fa fa-dribbble"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <div class="footer-copy text-center">
        <div class="container">
            <div class="row">
                <div class="pull-left">
                    <span> (C) <a href="http://www.KimaroTec.com">KimaroTheme</a> , All rights reserved 2016  </span>
                </div>
                <div class="bottom-menu pull-right">
                    <ul>
                        <li><a class="wow fadeInUp animated" href="/home" data-wow-delay="0.2s">Home</a></li>
                        <li><a class="wow fadeInUp animated" href="/login" data-wow-delay="0.3s">Employé</a></li>
                        <li><a class="wow fadeInUp animated" href="#" data-wow-delay="0.4s">Faq</a></li>
                        <li><a class="wow fadeInUp animated" href="<%out.print(link + "?what=contacter");%>"
                               data-wow-delay="0.6s">Contact</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>