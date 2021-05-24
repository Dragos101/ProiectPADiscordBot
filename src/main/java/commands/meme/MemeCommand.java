package commands.meme;

import com.fasterxml.jackson.databind.JsonNode;
import commands.CommandContext;
import commands.ICommand;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class MemeCommand extends ListenerAdapter implements ICommand {

    public void onGuildMessageReceived(GuildMessageReceivedEvent guildMessageReceivedEvent){
        List<String> args = Arrays.asList(guildMessageReceivedEvent.getMessage().getContentRaw().split("\\s+"));
        CommandContext commandContext = new CommandContext(guildMessageReceivedEvent, args);
        if(args.get(0).equalsIgnoreCase("~meme")) handle(commandContext);
    }

    @Override
    public void handle(CommandContext commandContext) {
        JSONParser jsonParser = new JSONParser();
        String postlink = "";
        String title = "";
        String url = "";
        try {
            URL memeURL = new URL("https://meme-api.herokuapp.com/gimme");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(memeURL.openConnection().getInputStream()));
            String lines;
            while((lines = bufferedReader.readLine()) != null){
                JSONArray array = new JSONArray();
                array.add(jsonParser.parse(lines));
                for(Object e : array){
                    JSONObject jsonObject = (JSONObject) e;
                    postlink = (String) jsonObject.get("postlink");
                    title = (String) jsonObject.get("title");
                    url = (String) jsonObject.get("url");
                }
            }
            bufferedReader.close();
            commandContext.getEvent().getMessage().delete().queue();
            EmbedBuilder builder = new EmbedBuilder().setTitle(title, postlink).setImage(url).setColor(Color.orange);
            commandContext.getEvent().getChannel().sendMessage(builder.build()).queue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "meme";
    }

    @Override
    public String getHelp() {
        return "Afiseaza un meme.";
    }
}
