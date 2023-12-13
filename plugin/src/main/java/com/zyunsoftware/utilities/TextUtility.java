package com.zyunsoftware.utilities;

import java.util.Random;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.md_5.bungee.api.ChatColor;

public class TextUtility {
  public static String serializeMessage(Component message) {
    return LegacyComponentSerializer.legacyAmpersand().serialize(message);
  }

  public static String getRandomString(String[] strings) {
    if (strings == null || strings.length == 0) {
      return null;
    }

    Random random = new Random();
    int randomIndex = random.nextInt(strings.length);

    return strings[randomIndex];
  }

  public static String getCustomNickname(String nickname) {
    if (nickname.equals("Ziozyun")) {
      nickname = "§eЗьозюн";
    }

    if (nickname.equals("Barsuku")) {
      nickname = "§eБорсук";
    }

    if (nickname.equals("Han__Salo")) {
      nickname = "§eХан Салович";
    }

    return "§e" + nickname;
  }

  public static String replaceSubstrings(String text) {
    text = text
      .replaceAll("&", "§")
      .replaceAll("хуй", "пісюньчик")
      .replaceAll("Ё", "Йо")
      .replaceAll("ё", "йо")
      .replaceAll("ъ", "ь")
      .replaceAll("ы", "и")
      .replaceAll("Э", "Є")
      .replaceAll("э", "є");

    return text;
  }

  public static String nobodyHeard() {
    String result = getRandomString(new String[] {
      "Ваші слова – це важкі трюки для вух, де ніхто не може визначити, як вони виконуються.",
      "Ви просто знаєте, як робити так, щоб ваш голос ставав на одну лінію зі здаваним книжковим мовчанням.",
      "Коли ви говорите, то навколо вас таке мовчання, що навіть метелики намагаються вас скопіювати.",
      "Ви як секрет з космосу: невловимі для вух, але завжди поруч.",
      "Ваші слова летять так тихо, що радіохвилі ревнують вас за їхню невагомість."
    });

    return ChatColor.RED + result;
  }

  public static String action() {
    String result = getRandomString(new String[] {
      "танцює гопака",
      "каже \"Гарна Українка я\""
    });

    return ChatColor.YELLOW + result;
  }

  public static String youWhisperTo(String nickname) {
    String result = getRandomString(new String[] {
      "%s §eотримав шептуна",
      "%s §eотримав поштового голуба"
    });

    nickname = getCustomNickname(nickname);
    result = String.format(result, nickname);

    return result;
  }

  public static String whisperFrom(String nickname, String message) {
    String result = getRandomString(new String[] {
      "§7Вам %s §7надокучливо нашіптує: %s",
      "§7Вам %s §7телепатично говорить: %s",
      "§7Вам %s §7еротично шепоче: %s",
      "§7Вам %s §7прислав голуба з повідомленням: %s",
      "§7Вам %s §7курликнув під вушко: %s"
    });

    nickname = getCustomNickname(nickname);
    message = replaceSubstrings(message);
    result = String.format(result, nickname, message);

    return result;
  }

  public static String recipientNotFound() {
    String result = getRandomString(new String[] {
      "Отримувача не знайдено"
    });

    return ChatColor.RED + result;
  }
}
