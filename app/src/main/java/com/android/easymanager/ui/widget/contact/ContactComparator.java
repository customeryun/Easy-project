package com.android.easymanager.ui.widget.contact;

import com.android.easymanager.database.FriendEntry;
import java.util.Comparator;

/**
 * 按照字母进行排序
 */
public class ContactComparator implements Comparator<FriendEntry> {

    @Override
    public int compare(FriendEntry o1, FriendEntry o2) {
        int c1 = (o1.username.charAt(0) + "").toUpperCase().hashCode();
        int c2 = (o2.username.charAt(0) + "").toUpperCase().hashCode();

        boolean c1Flag = (c1 < "A".hashCode() || c1 > "Z".hashCode()); // 不是字母
        boolean c2Flag = (c2 < "A".hashCode() || c2 > "Z".hashCode()); // 不是字母
        if (c1Flag && !c2Flag) {
            return 1;
        } else if (!c1Flag && c2Flag) {
            return -1;
        }

        return c1 - c2;
    }

}
