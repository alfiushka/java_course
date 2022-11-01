package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.sql.*;

public class DbConnectionTest {

    @Test
    public void testDbConnection () {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select group_id, group_name, group_header, group_footer from group_list");
            Groups groups = new Groups();
            while (rs.next()) {
                groups.add(new GroupData().withId(rs.getInt("group_id")).withName(rs.getString("group_name"))
                        .withHeader(rs.getString("group_header")).withFooter(rs.getString("group_footer")));
            }

            ResultSet rsContact = st.executeQuery("select id, firstname, lastname, address, home, email from addressbook");
            Contacts contacts = new Contacts();
            while (rsContact.next()) {
                contacts.add(new ContactData().withId(rsContact.getInt("id")).withFirstName(rsContact.getString("firstname"))
                        .withLastName(rsContact.getString("lastname")).withAddress(rsContact.getString("address"))
                        .withHomePhone(rsContact.getString("home")).withEmail1(rsContact.getString("email")));
            }
            rsContact.close();

            rs.close();
            st.close();
            conn.close();

           System.out.println(groups);
           System.out.println(contacts);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
