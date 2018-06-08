package bot;

import java.io.IOException;

public class Main {

    public static void main(String [] args) throws IOException, InterruptedException {
        Bot bot = new Bot();
        bot.LogIn("KUBEK.BREHA", "");
        bot.followUsers(bot.getUserFollowingPK(bot.getUser("popugram")));
    }
}
