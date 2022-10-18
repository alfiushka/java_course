package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {
//        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(("src/test/resources/groups.xml")));
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
            xml += line;
            line = reader.readLine();
        }
        XStream xstream = new XStream();
        xstream.processAnnotations(GroupData.class);
        xstream.allowTypes(new Class[] {GroupData.class});
        List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();

//        list.add(new Object[] {new GroupData().withName("test1").withHeader("header 1").withFooter("footer 1")});
//        list.add(new Object[] {new GroupData().withName("test2").withHeader("header 2").withFooter("footer 2")});
//        list.add(new Object[] {new GroupData().withName("test3").withHeader("header 3").withFooter("footer 3")});
    }

  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) throws Exception {
//      GroupData group = new GroupData().withName(name).withHeader(header).withFooter(footer);
    app.goTo().groupPage();
   Groups before = app.group().all();
//    GroupData group = new GroupData().withName("test2");
    app.group().create(group);
    app.goTo().groupPage();
    Groups after = app.group().all();
    assertThat(after.size(), equalTo(before.size() +1));
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
