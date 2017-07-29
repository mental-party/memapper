# MeMapper
A Simple Generic Java Bean Object Mapper

##How to use

MeMapper is developed for a handy usage. It is as simple as this:

* Example:
> Optional&lt;Target&gt; target = MeMapper.getMapperFrom(source).mapTo(Target.class); //requires a public no-args constructor


* Mapping with Lists:

> Optional&lt;List&lt;Target&gt;&gt; targets = MeMapper.getMapperFromList(sources).mapToList(Target.class);

###Important points
* Source and target classes must fit with Java Bean specs.
* MeMapper maps fields which have the same name and type in both source and target types.
* Only those fields which have `public` getters will be used when mapping from source.
* Only those fields which have `public` setters will be used when mapping to target.

Example:

Source class:
>public class Source
>{
>
>     private String title;
>     private Integer id;
>     private List<String> list;
>     
>          
>     public String getTitle() {
>          return title;
>     }
>     
>          
>     public void setTitle(String title) {
>          this.title = title;
>     }
>     
>          
>     public Integer getId() {
>          return id;
>     }
>     
>          
>     public void setList(List<String> list) {
>          this.list = list;
>     }
>
>      
>     public Source(Integer id) {
>          this.id = id;
>     }
>
>}

Target class:
>public class Target
>{
>
>     private String title;
>     private Integer id;
>     private List<String> list;
>     private Double ratio;
>     
>          
>     public String getTitle() {
>          return title;
>     }
>     
>          
>     public void setTitle(String title) {
>          this.title = title;
>     }
>     
>          
>     public Integer getId() {
>          return id;
>     }
>     
>          
>     public void setId(Integer id) {
>          this.id = id;
>     }
>     
>        
>     public List<String> getList() {
>          return list;
>     }
>
>      
>     public void setList(List<String> list) {
>          this.list = list;
>     }
>     
>          
>     public Double getRatio() {
>          return ratio;
>     }
>     
>          
>     public void setRatio(Double ratio) {
>          this.ratio = ratio;
>     }
>
>}


Mapping:
>     Source source = new Source(1);
>     source.setTitle("title");
>     source.setList(Arrays.asList("1","2"));
>      
>     Optional<Target> target = MeMapper.getMapperFrom(source).mapTo(Target.class);
>     assert target.getId() == 1; //true
>     assert target.getTitle() == "title"; //true
>     assert target.getList() == null; //true
>     assert target.getRatio() == null; //true

If Source class implements `Mappable` interface; instead of 

`MeMapper.getMapperFrom(source).mapTo(Target.class);`
 
 you can use `source.mapTo(Target.class);`.



#For Developer
Build and generate publication.

<code>
 ./gradlew clean build generatePomFileForBintrayPublicationPublication -DbambooPublishNumber=0
</code>


### After publish to bintray

<code>
./gradlew -Pbintray-user=coskundeniz -Pbintray-key=**************** bintrayUpload -x clean
</code>