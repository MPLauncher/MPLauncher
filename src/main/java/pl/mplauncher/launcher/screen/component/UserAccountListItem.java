package pl.mplauncher.launcher.screen.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import org.ocpsoft.prettytime.PrettyTime;
import pl.mplauncher.launcher.api.i18n.MessageBundle;
import pl.mplauncher.launcher.config.UserProfile;

import java.util.Date;

public class UserAccountListItem extends StackPane {

    public UserAccountListItem(UserProfile user) {
        this.setMouseTransparent(true);

        HBox inner = new HBox();

        ImageView avatar = new ImageView();
        avatar.setFitWidth(32.0);
        avatar.setFitHeight(32.0);
        avatar.setPickOnBounds(true);
        avatar.setPreserveRatio(true);
        if (user.getUsername().equalsIgnoreCase("cebula")) {
            avatar.setImage(new Image("https://vignette.wikia.nocookie.net/disneycreate/images/5/51/Onion.png/revision/latest"));
        } else {
            avatar.setImage(new Image("https://skiny.mplauncher.pl/api/3d.php?user=" + user.getUsername() + "&vr=0&hr=0&displayHair=true&headOnly=true&format=png&ratio=20&aa=true&layers=false"));
        }

        VBox info = new VBox();
        HBox.setMargin(info, new Insets(2.0, 0.0, 0.0, 8.0));

        Label username = new Label();
        username.setText(user.getUsername());
        username.getStyleClass().addAll("fontRegular", "fontSize12", "textFillWhite");

        PrettyTime prettyTime = new PrettyTime();
        prettyTime.setLocale(MessageBundle.getCurrentLanguage().getLocale());

        Label lastLoggedIn = new Label();
        lastLoggedIn.setText(prettyTime.format(new Date(user.getLastLogin())));
        lastLoggedIn.getStyleClass().addAll("fontLight", "fontSize10", "textFillLightGray");

        Label accountType = new Label();
        accountType.setText(user.getUserType().name());
        accountType.setTextAlignment(TextAlignment.RIGHT);
        accountType.getStyleClass().addAll("fontSemiBold", "fontSize10", "textFillWhite");
        StackPane.setAlignment(accountType, Pos.TOP_RIGHT);
        StackPane.setMargin(accountType, new Insets(2.0, 2.0, 0.0, 0.0));

        this.getChildren().addAll(inner, accountType);
        inner.getChildren().addAll(avatar, info);
        info.getChildren().addAll(username, lastLoggedIn);
    }

}