package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
            app.goTo().groupPage();
        }
    }

    @Test
    public void testGroupModification () {
        Set<GroupData> before = app.group().all();
        GroupData modifiedGroup =before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test1edit").withHeader("test2edit").withFooter("test3edit");
        app.group().modify(group);
        app.goTo().groupPage();
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedGroup);
        before.add(group);
//        Comparator<? super GroupData> byId= (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//        before.sort(byId);
//        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
