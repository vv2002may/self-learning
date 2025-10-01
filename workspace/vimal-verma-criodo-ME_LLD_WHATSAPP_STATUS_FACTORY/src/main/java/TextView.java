public class TextView implements View{

    private String content;


    public TextView(String content) {
        this.content = content;
    }

    @Override
    public void displayMedia() {
        System.out.println("Displaying text: "+content);
        
    }

    @Override
    public void setContentForMedia(String content) {
       this.content=content;
        
    }

}