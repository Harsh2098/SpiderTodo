package com.hmproductions.spidertodo;

/**
 * Created by Harsh Mahajan on 7/6/2017.
 */

class Item {

    private int mRowNumber;

    private String mItemName;

    Item (int RowNumber, String ItemName)
    {
        mRowNumber = RowNumber;
        mItemName = ItemName;
    }

    int getRowNumber() {
        return mRowNumber;
    }

    String getItemName() {
        return mItemName;
    }

    void setItemName(String itemName)
    {
        mItemName = itemName;
    }
}
