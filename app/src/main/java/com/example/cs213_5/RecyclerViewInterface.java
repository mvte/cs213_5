package com.example.cs213_5;

/**
 * This interface ensures the definition of any behaviors required by a Fragment/Activity
 * containing a RecyclerView
 * @author Jan Marzan, Brian Zhang
 */
public interface RecyclerViewInterface {

    /**
     * Defines behavior of the program when an item of the RecyclerView is clicked.
     * @param position the position of the item being clicked
     */
    void onItemClick(int position);

}

