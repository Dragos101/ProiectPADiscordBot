import commands.Clear;
import commands.events.GuildMemberJoin;
import commands.events.GuildMemberRemove;
import commands.events.GuildMessageReactionAdd;
import commands.events.GuildMessageReceived;
import commands.Info;
import commands.meme.MemeCommand;
import commands.music.JoinCommand;
import commands.music.LeaveCommand;
import commands.music.PlayCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Main {

    public static JDA jda;
    public static void main(String[] args) throws LoginException {

        jda = JDABuilder.createDefault("ODM5ODY1NDUzNjA1OTQ1NDE0.YJP3qA.aYG82heaF9I34pPjn-I24J01_LQ").enableIntents(GatewayIntent.GUILD_MEMBERS).build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.listening("commands"));


        jda.addEventListener(new GuildMemberRemove());
        jda.addEventListener(new Clear());
        jda.addEventListener(new Info());
        jda.addEventListener(new GuildMemberJoin());
        jda.addEventListener(new GuildMessageReceived());
        jda.addEventListener(new GuildMessageReactionAdd());
        jda.addEventListener(new JoinCommand());
        jda.addEventListener(new LeaveCommand());
        jda.addEventListener(new PlayCommand());
        jda.addEventListener(new MemeCommand());
    }
}
