module sprint2 {
    requires java.desktop;
    
    exports app;
    opens app;  // This allows reflection access for testing
}