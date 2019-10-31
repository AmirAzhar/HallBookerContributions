package command;

import booking.BookingList;
import exception.DukeException;
import inventory.Inventory;
import room.RoomList;
import storage.Constants;
import storage.Storage;
import ui.Ui;
import user.UserList;

import java.io.IOException;

public class RemoveUserCommand extends Command {
    private String[] splitL;

    //@@ AmirAzhar
    /**
     * User login.
     * @param input from user
     * @param splitStr tokenized input
     * @throws DukeException if format not followed
     */
    public RemoveUserCommand(String input, String[] splitStr) throws DukeException {
        if (splitStr.length == 1) {
            throw new DukeException(Constants.UNHAPPY + " OOPS!!! Please enter a username you would like to remove!");
        }
        this.splitL = input.split(" ");
    }

    @Override
    public void execute(UserList userList, Inventory inventory, RoomList roomList, BookingList bookingList, Ui ui,
                        Storage userStorage, Storage inventoryStorage, Storage bookingstorage, Storage roomstorage)
            throws DukeException, IOException {
        boolean isValid = UserList.checkExistence(userList, splitL[1]);
        if (isValid) {
            if (userList.currentUser.equals(splitL[1])) {
                throw new DukeException("OOPS! You are currently logged in as " + userList.currentUser + "!");
            } else {
                UserList.removeUser(userList, splitL[1]);
                userStorage.saveToFile(userList);
                ui.addToOutput("You have successfully removed user: " + splitL[1]);
            }
        } else {
            throw new DukeException("The user you are trying to remove does not exist!");
        }

    }
}
