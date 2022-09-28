package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToGroupPage();
        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
            app.getNavigationHelper().goToGroupPage();
        }
    }

    @Test
    public void testGroupModification () {
        List<GroupData> before = app.getGroupHelper().getGroupList();
//        GroupData createGroup = new GroupData("test1", "test2", "test3");
        int index = before.size() -1;
        GroupData group = new GroupData(before.get(index).getId(), "test1edit", "test2edit", "test3edit");
        app.getGroupHelper().modifyGroup(index, group);
        app.getNavigationHelper().goToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(group);
        Comparator<? super GroupData> byId= (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
