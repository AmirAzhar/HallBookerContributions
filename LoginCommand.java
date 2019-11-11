
package command;

import booking.ApprovedList;
import booking.BookingList;
import exception.DukeException;
import inventory.Inventory;
import room.RoomList;
import storage.Constants;
import storage.Storage;
import storage.StorageManager;
import ui.Ui;
import user.UserList;

//@@author AmirAzhar
public class LoginCommand extends Command {
    private String[] splitL;


    /**
     * User login.
     * @param input from user
     * @param splitStr tokenized input
     * @throws DukeException if format not followed
     */
    public LoginCommand(String input, String[] splitStr) throws DukeException {
        if (splitStr.length == 1) {
            throw new DukeException(Constants.UNHAPPY + " OOPS!!! Please login with your username!");
        }
        this.splitL = input.split("login ");
    }

    @Override
    public void execute(UserList userList, Inventory inventory, RoomList roomList,
                        BookingList bookingList, ApprovedList approvedList, Ui ui,
                        StorageManager allStorage)
            throws DukeException {
        if (!userList.getLoginStatus()) {
            if (userList.login(splitL[1].trim())) {
                ui.addToOutput("You have successfully logged in as: " + userList.getCurrentUser());
            } else {
                throw new DukeException("The user does not exist!");
            }
        } else {
            throw new DukeException("You are already logged in!");
        }
    }
}
