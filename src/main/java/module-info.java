module minesweeper.main {
    requires java.datatransfer;
    requires java.desktop;

    requires jdk.unsupported;

    exports components;
    exports controllers;
    exports drivers;
    exports handlers.cells;
    exports handlers.difficulty;
    exports handlers.status;
    exports handlers.timer;
    exports models;
    exports utils;
}