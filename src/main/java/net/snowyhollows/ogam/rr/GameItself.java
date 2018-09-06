package net.snowyhollows.ogam.rr;

import net.snowyhollows.bento2.annotation.WithFactory;
import net.snowyhollows.ogam.rr.factory.AllFactoryConfigs;
import net.snowyhollows.ogam.rr.feature.ai.system.ActorSystem;
import net.snowyhollows.ogam.rr.feature.ascii.system.AsciiDisplaySystem;
import net.snowyhollows.ogam.rr.feature.combat.system.DestructibleSystem;
import net.snowyhollows.ogam.rr.feature.combat.system.GradientSystem;
import net.snowyhollows.ogam.rr.feature.space.LevelGenerator;
import net.snowyhollows.ogam.rr.feature.space.component.DisplayListSystem;
import net.snowyhollows.ogam.rr.feature.space.system.FovSystem;

public class GameItself {
    private final AllFactoryConfigs allFactoryConfigs;
    private final LevelGenerator levelGenerator;
    private final DestructibleSystem destructibleSystem;
    private final ActorSystem actorSystem;
    private final GradientSystem gradientSystem;
    private final DisplayListSystem displayListSystem;
    private final AsciiDisplaySystem asciiDisplaySystem;
    private final PlayerSystem playerSystem;
    private final FovSystem fovSystem;
    private final MessagePanel messagePanel;

    @WithFactory
    public GameItself(AllFactoryConfigs allFactoryConfigs,
                      LevelGenerator levelGenerator,
                      DestructibleSystem destructibleSystem,
                      ActorSystem actorSystem,
                      GradientSystem gradientSystem,
                      DisplayListSystem displayListSystem,
                      AsciiDisplaySystem asciiDisplaySystem, PlayerSystem playerSystem,
                      FovSystem fovSystem,
                      MessagePanel messagePanel) {

        this.allFactoryConfigs = allFactoryConfigs;
        this.levelGenerator = levelGenerator;
        this.destructibleSystem = destructibleSystem;
        this.actorSystem = actorSystem;
        this.gradientSystem = gradientSystem;
        this.displayListSystem = displayListSystem;
        this.asciiDisplaySystem = asciiDisplaySystem;
        this.playerSystem = playerSystem;
        this.fovSystem = fovSystem;
        this.messagePanel = messagePanel;

        levelGenerator.generate();
    }

    public void step() {
        destructibleSystem.run();
        fovSystem.run();
        actorSystem.run();
        gradientSystem.run();
        displayListSystem.run();
        fovSystem.run();
        asciiDisplaySystem.run();
        messagePanel.run();
    }

    public void execute(PlayerCommand playerCommand) {
        playerSystem.run(playerCommand);
    }
}
