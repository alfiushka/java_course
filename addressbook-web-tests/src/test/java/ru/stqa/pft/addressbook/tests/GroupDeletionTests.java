package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }
  @Test
  public void testGroupDeletion() throws Exception {
    Groups before = app.db().groups();
    GroupData deleteGroup =before.iterator().next();
    app.goTo().groupPage();
    app.group().delete(deleteGroup);
    Groups after = app.db().groups();
    Assert.assertEquals(after.size(), before.size() -1);
    assertThat(after, equalTo(before.without(deleteGroup)));
  }
}
