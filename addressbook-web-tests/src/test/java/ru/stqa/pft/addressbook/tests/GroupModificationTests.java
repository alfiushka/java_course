package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupModification () {
        Groups before = app.db().groups();
        GroupData modifiedGroup =before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test1edit").withHeader("test2edit").withFooter("test3edit");
        app.goTo().groupPage();
        app.group().modify(group);
        Groups after = app.db().groups();
        Assert.assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
        app.goTo().groupPage();
        verifyGroupListInUI();
    }


}
