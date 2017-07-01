package com.teammental.memapper;

import com.teammental.memapper.util.mapping.MapByFieldNameUtil;

import java.util.Optional;

/**
 * Mapper is used for mapping from a SourceT source object
 * to a TargetT object in an easy and readible way.
 */
public class MeMapper<SourceT, TargetT> implements MapTo<TargetT>, MapToList<TargetT> {
  private SourceT source;
  private Iterable<SourceT> sources;

  private MeMapper(SourceT source) {
    this.source = source;
  }

  private MeMapper(Iterable<SourceT> sources) {
    this.sources = sources;
  }


  /**
   * Creates a new Mapper instance.
   *
   * @param source SourceT object which will be used
   *               as source when mapping.
   * @return a new Mapper instance.
   */
  public static <SourceT> MapTo getMapperFrom(SourceT source) {
    return new MeMapper(source);
  }

  /**
   * Creates a new Mapper instance.
   *
   * @param sources List&lt;SourceT&gt; object which will be used
   *                as source when mapping.
   * @return a new Mapper instance.
   */
  public static <SourceT> MapToList getMapperFromList(Iterable<SourceT> sources) {
    return new MeMapper(sources);
  }


  /**
   * Maps the given source SourceT object
   * to a newly instantiated object of the given TargetType.
   *
   * @param targetType Target object's class.
   *                   Target type must have a public no-arg
   *                   constructor. Otherwise a TargetTypeInstantiationException
   *                   will be thrown.
   * @return Optional&lt;Iterable&ltTargetT&gt;&gt;
   */
  @Override
  public Optional<Iterable<TargetT>> mapToList(Class<TargetT> targetType) {
    try {
      return Optional.ofNullable(MapByFieldNameUtil.map(sources, targetType));
    } catch (Exception e) {
      return Optional.empty();
    }
  }


  /**
   * Maps the given source SourceT object
   * to a newly instantiated object of the given TargetType.
   *
   * @param targetType Target object's class.
   *                   Target type must have a public no-arg
   *                   constructor. Otherwise a TargetTypeInstantiationException
   *                   will be thrown.
   * @return Optional&lt;TargetT&gt;.
   */
  @Override
  public Optional<TargetT> mapTo(Class<TargetT> targetType) {
    try {
      return Optional.ofNullable(MapByFieldNameUtil.map(source, targetType));
    } catch (Exception e) {
      return Optional.empty();
    }
  }


}
