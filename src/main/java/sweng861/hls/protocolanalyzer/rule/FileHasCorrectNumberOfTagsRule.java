/**
 * 
 */
package sweng861.hls.protocolanalyzer.rule;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.functors.EqualPredicate;

import sweng861.hls.protocolanalyzer.annotation.FollowedBy;
import sweng861.hls.protocolanalyzer.annotation.Times;
import sweng861.hls.protocolanalyzer.annotation.TimesType;
import sweng861.hls.protocolanalyzer.evaluator.ErrorType;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.MediaFileTagType;

/**
 * @author Scott
 *
 */
class FileHasCorrectNumberOfTagsRule extends AbstractMediaFileRule {

	/* (non-Javadoc)
	 * @see sweng861.hls.protocolanalyzer.rule.HLSRule#runRuleCheck(sweng861.hls.protocolanalyzer.file.HLSMediaFile)
	 */
	public void runRuleCheck(HLSMediaFile file) {
		List<MediaFileTagType> tagList = file.getTagList();
		List<MediaFileTagType> tagsWithTimesConstraint = MediaFileTagType.getTagsWithTimesConstraint();
		for (MediaFileTagType tag : tagsWithTimesConstraint){
			Predicate predicate = EqualPredicate.getInstance(tag);
			int matches = CollectionUtils.countMatches(tagList, predicate);
			TimesType numberOfTimesType = getNumberOfTimesType(tag);
			if(numberOfTimesType != null && !numberOfTimesType.evaluate(matches)) {
				super.addToErrorLog(file, 
						ErrorType.TAG_HAS_INVALID_NUMBER_OF_OCCURENCES.getSeverity(), 
						String.format(ErrorType.TAG_HAS_INVALID_NUMBER_OF_OCCURENCES.getMessageFormat(), tag.name(), matches, numberOfTimesType.name()));
			}
			
		}

	}
	
	public TimesType getNumberOfTimesType(MediaFileTagType currentTag){
		Times times = null;
		try {
			times = currentTag.getClass().getField(currentTag.name()).getAnnotation(Times.class);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		if (times != null){
			return times.value();
		}
		return null;
	}
	

}
