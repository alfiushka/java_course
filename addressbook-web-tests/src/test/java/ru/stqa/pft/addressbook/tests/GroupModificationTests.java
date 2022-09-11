package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase{

    @Test
    public void testGroupModification () {
        app.getNavigationHelper().goToGroupPage();
        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
            app.getNavigationHelper().goToGroupPage();
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test1edit", "test2edit", "test3edit"));
        app.getGroupHelper().submitGroupModification();
        app.getNavigationHelper().goToGroupPage();
    }
}
