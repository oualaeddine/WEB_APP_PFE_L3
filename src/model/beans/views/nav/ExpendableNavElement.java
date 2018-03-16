package model.beans.views.nav;

import model.beans.views.MyView;

import java.util.LinkedList;

public class ExpendableNavElement extends MyView {

    private String subElementsListId;
    private String icon;
    private String title;
    private LinkedList<NavElement> subElementsList;
    private boolean isPage;

    public ExpendableNavElement(String subElementsListId, String icon, String title, LinkedList<NavElement> subElementsList, boolean isPage) {
        this.subElementsListId = subElementsListId;
        this.icon = icon;
        this.title = title;
        this.subElementsList = subElementsList;
        this.isPage = isPage;
    }

    private String getCssClass() {
        if (isPage)
            return " nav-item nav-item-selected";
        else
            return " nav-item ";
    }

    @Override
    public String getHtml() {
//        String html = "            <li class=\"" + getCssClass() + "\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"" + getTitle() + "\">\n" +
//                "                <a class=\"nav-link nav-link-collapse collapsed \" data-toggle=\"collapse\" href=\"#colapseComponents\" data-parent=\"#exampleAccordion\">\n" +
//                "                    <i class=\"fa fa-fw " + getIcon() + "\"></i>\n" +
//                "                    <span class=\"nav-link-text\">" + getTitle() + "</span>\n" +
//                "                </a>\n";
//        for (NavElement navElement: getSubElementsList()) {
//            html=html+navElement.getHtml();
//        }
//        html
////                "            </li>";
        return "<li class=\"nav-item " + isSelected() + "\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"" + getTitle() + "\">\n" +
                "                <a class=\"nav-link nav-link-collapse " + isCollapsed() + "\" data-toggle=\"collapse\" href=\"#" + getSubElementsListId() + "\" data-parent=\"#exampleAccordion\">\n" +
                "                    <i class=\"fa fa-fw " + getIcon() + "\"></i>\n" +
                "                    <span class=\"nav-link-text\">" + getTitle() + "</span>\n" +
                "                </a>\n" +
                "                <ul class=\"sidenav-second-level collapse\" id=\"" + getSubElementsListId() + "\">\n" +
                getSubElementsHtml() +
                "            </ul></li>";
    }

    private String isSelected() {
        if (isPage)
            return " nav-item nav-item-selected";
        else
            return " nav-item ";
    }

    private String getSubElementsListId() {
        return subElementsListId;
    }

    private String getSubElementsHtml() {
        String subElementsHtml = "";
        for (NavElement navElement : subElementsList)
            subElementsHtml = subElementsHtml+navElement.getHtml();
//            subElementsHtml.concat(navElement.getHtml());
        return subElementsHtml;
    }

    public String getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }

    private String isCollapsed() {
        if (isPage)
            return "collapsed";
        else
            return "collapsed";
    }

    public void setSubElementsListId(String subElementsListId) {
        this.subElementsListId = subElementsListId;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LinkedList<NavElement> getSubElementsList() {
        return subElementsList;
    }

    public void setSubElementsList(LinkedList<NavElement> subElementsList) {
        this.subElementsList = subElementsList;
    }

    public boolean isPage() {
        return isPage;
    }

    public void setPage(boolean page) {
        isPage = page;
    }
}
