package model.db.daos;

import model.beans.humans.Employe;
import model.db.DAO;
import utils.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

@SuppressWarnings("ALL")
public class EmployeDAO extends DAO {

    public boolean inscriptionEmploye(Employe employe) {
        try {
            employeStatement.execute("INSERT INTO employe (`nom`,`prenom`,`dateNaiss`,`adresse`,`tel`,`email`,`username`, `password`,`isApproved`,`isSuspended` ) VALUES(" +
                    "'" + employe.getNom() + "'," +
                    "'" + employe.getPrenom() + "'," +
                    "'" + employe.getDateNaissance() + "'," +
                    "'" + employe.getAdresse() + "'," +
                    "'" + employe.getTel() + "'," +
                    "'" + employe.getEmail() + "'," +
                    "'" + employe.getUsername() + "'," +
                    "'" + employe.getPassword() + "'," +
                    "0, " +
                    "0" +
                    ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean approuverEmploye(int id, int myId, String userType) {
        try {
            employeStatement.execute("UPDATE employe SET isApproved = 1, addedBy = "+myId+",userType='"+userType+"',dateAdded=CURRENT_DATE WHERE id="+id+";");
//            employeStatement.execute("UPDATE employe SET isApproved=1 WHERE id=" + id + ";");
//            employeStatement.execute("UPDATE employe SET addedBy = "+ myId +" WHERE id="+ id +";");
//            employeStatement.execute("UPDATE employe SET userType = '" + userType + "' WHERE id=" + id + ";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean changePassword(int id, String pass) {
        try {
            employeStatement.execute("UPDATE employe SET password='" + pass + "' WHERE id=" + id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Employe getByUsername(String username) {
        ResultSet result;
        try {
            result = employeStatement.executeQuery("SELECT * FROM employe WHERE username='" + username + "'");
            if (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setDateAdded(result.getDate("dateAdded"));
                employe.setApproved(result.getBoolean("isApproved"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));

                return employe;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employe getByEmail(String email) {
        ResultSet result;
        try {
            result = employeStatement.executeQuery("SELECT * FROM employe WHERE email = '"+email+"';");
            if (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setApproved(result.getBoolean("isApproved"));
                if (employe.isApproved()) {
                    employe.setDateAdded(result.getDate("dateAdded"));
                    employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));
                    employe.setCreator(result.getInt("addedBy"));
                }
                return employe;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object getById(int id) {
        ResultSet result;
        try {
            result = employeStatement.executeQuery("SELECT * FROM employe WHERE id=" + id);
            if (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setApproved(result.getBoolean("isApproved"));
                if (employe.isApproved()) {
                    employe.setDateAdded(result.getDate("dateAdded"));
                    employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));
                    employe.setCreator(result.getInt("addedBy"));
                }
                return employe;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean deleteById(int id) {
        try {
            employeStatement.execute("DELETE FROM employe WHERE id=" + id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean add(Object object) {
        Employe employe = (Employe) object;
        try {
            employeStatement.execute("INSERT INTO employe (`nom`,`prenom`,`dateNaiss`,`adresse`,`tel`,`email`,`username`, `password`,`dateAdded`,`addedBy`,`isSuspended`,`userType` ) VALUES(" +
                    "'" + employe.getNom() + "'," +
                    "'" + employe.getPrenom() + "'," +
                    "'"+employe.getDateNaissance() +"'"+ "," +
                    "'" + employe.getAdresse() + "'," +
                    "'" + employe.getTel() + "'," +
                    "'" + employe.getEmail() + "'," +
                    "'" + employe.getUsername() + "'," +
                    "'" + employe.getPassword() + "'," +
                    "CURRENT_DATE" + "," +
                    employe.getCreator() + "," +
                    employe.isSuspended() + "," +
                    "'"+Util.getStringFromType(employe.getUserType())+"'"+
                    ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }

    public LinkedList<Employe> getAll() {
        LinkedList<Employe> list = new LinkedList<>();
        ResultSet result;
        try {
            result = employeStatement.executeQuery("SELECT * FROM employe;");
            while (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setDateAdded(result.getDate("dateAdded"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));

                list.add(employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void countAll() {

    }

    public boolean authenticate(String username, String password) {
        ResultSet result;
        try {
            result = employeStatement.executeQuery("SELECT id,isSuspended FROM employe WHERE ((username='" + username + "' AND password='" + password + "') OR (email='"+username+"' AND password='"+password+"')) AND isSuspended=0");
            return (result.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean reintegrerById(int id) {
        return super.reintegrerById(id,"employe");
    }

    public boolean suspendById(int userId) {
        return super.suspendById(userId, "employe");
    }

    public LinkedList<Employe> getSuspendedResVente() {
        LinkedList<Employe> list = new LinkedList<>();
        ResultSet result;
        try {
            result = employeStatement.executeQuery("SELECT * FROM employe WHERE userType='responsable_vente' AND isSuspended=1 AND isApproved=1;");
            while (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setApproved(result.getBoolean("isApproved"));
                if (employe.isApproved()) {
                    employe.setDateAdded(result.getDate("dateAdded"));
                    employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));
                    employe.setCreator(result.getInt("addedBy"));
                }
                list.add(employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Employe> getAllResVentes() {
        ResultSet result;
        LinkedList<Employe> list = new LinkedList<>();
        try {
            result = employeStatement.executeQuery("SELECT * FROM employe WHERE userType='responsable_ventes' AND isApproved=1;");
            while (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setApproved(result.getBoolean("isApproved"));
                if (employe.isApproved()) {
                    employe.setDateAdded(result.getDate("dateAdded"));
                    employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));
                    employe.setCreator(result.getInt("addedBy"));
                }
                list.add(employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Employe> getSuspendedOperateurs() {
        LinkedList<Employe> list = new LinkedList<>();
        ResultSet result;
        try {
            result = employeStatement.executeQuery("SELECT id FROM employe WHERE userType='operateur' AND isSuspended=1 AND isApproved=1;");
            while (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setApproved(result.getBoolean("isApproved"));
                if (employe.isApproved()) {
                    employe.setDateAdded(result.getDate("dateAdded"));
                    employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));
                    employe.setCreator(result.getInt("addedBy"));
                }
                list.add(employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public LinkedList<Employe> getAllOperateurs() {
        ResultSet result;
        LinkedList<Employe> list = new LinkedList<>();
        try {
            result = employeStatement.executeQuery("SELECT * FROM employe WHERE userType='operateur' AND isApproved=1;");
            while (result.next()) {

                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setApproved(result.getBoolean("isApproved"));
                if (employe.isApproved()) {
                    employe.setDateAdded(result.getDate("dateAdded"));
                    employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));
                    employe.setCreator(result.getInt("addedBy"));
                }

                list.add(employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Employe> getSuspendedAgents() {
        LinkedList<Employe> list = new LinkedList<>();
        ResultSet result;
        try {
            result = employeStatement.executeQuery("SELECT id FROM employe WHERE userType='agent' AND isSuspended=1 AND isApproved=1;");
            while (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setApproved(result.getBoolean("isApproved"));
                if (employe.isApproved()) {
                    employe.setDateAdded(result.getDate("dateAdded"));
                    employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));
                    employe.setCreator(result.getInt("addedBy"));
                }

                list.add(employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Employe> getAllAgents() {
        ResultSet result;
        LinkedList<Employe> list = new LinkedList<>();
        try {
            result = employeStatement.executeQuery("SELECT * FROM employe WHERE userType='agent' AND isApproved=1;");
            while (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setApproved(result.getBoolean("isApproved"));
                if (employe.isApproved()) {
                    employe.setDateAdded(result.getDate("dateAdded"));
                    employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));
                    employe.setCreator(result.getInt("addedBy"));
                }

                list.add(employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Employe> getAllAdmins() {
        ResultSet result;
        LinkedList<Employe> list = new LinkedList<>();
        try {
            result = employeStatement.executeQuery("SELECT * FROM employe WHERE (userType='admin' OR userType='SU') AND isApproved=1;");
            while (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setApproved(result.getBoolean("isApproved"));
                if (employe.isApproved()) {
                    employe.setDateAdded(result.getDate("dateAdded"));
                    employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));
                    employe.setCreator(result.getInt("addedBy"));
                }

                list.add(employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Employe> getSuspendedAdmins() {
        ResultSet result;
        LinkedList<Employe> list = new LinkedList<>();
        try {
            result = employeStatement.executeQuery("SELECT * FROM employe WHERE userType='admin' AND isSuspended=1 AND isApproved=1;");
            while (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setApproved(result.getBoolean("isApproved"));
                if (employe.isApproved()) {
                    employe.setDateAdded(result.getDate("dateAdded"));
                    employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));
                    employe.setCreator(result.getInt("addedBy"));
                }

                list.add(employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<Employe> getNotApprovedEmployees() {
        LinkedList<Employe> list = new LinkedList<>();
        ResultSet result;
        try {
            result = employeStatement.executeQuery("SELECT * FROM employe WHERE isApproved=0;");
            while (result.next()) {
                Employe employe = new Employe();
                employe.setId(result.getInt("id"));
                employe.setNom(result.getString("nom"));
                employe.setPrenom(result.getString("prenom"));
                employe.setDateNaissance(result.getDate("dateNaiss"));
                employe.setAdresse(result.getString("adresse"));
                employe.setTel(result.getString("tel"));
                employe.setEmail(result.getString("email"));
                employe.setUsername(result.getString("username"));
                employe.setPassword(result.getString("password"));
                employe.setSuspended(result.getBoolean("isSuspended"));
                employe.setApproved(result.getBoolean("isApproved"));
                if (employe.isApproved()) {
                    employe.setDateAdded(result.getDate("dateAdded"));
                    employe.setUserType(Util.getUserTypeFromString(result.getString("userType")));
                    employe.setCreator(result.getInt("addedBy"));
                }

                list.add(employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


}
