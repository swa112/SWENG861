package sweng861.hls.protocolanalyzer.annotation;

public enum TimesType {
	
	ONCE {
		@Override
		public boolean evaluate(int numberOfTimes) {
			if (numberOfTimes <= 1){
				return true;
			}
			return false;
		}
	}, 
	
	MORE_THAN_ONCE {
		@Override
		public boolean evaluate(int numberOfTimes) {
			if (numberOfTimes == 0 || numberOfTimes > 1){
				return true;
			}
			return false;
		}
	},
	
	
	;
	
	public abstract boolean evaluate(int numberOfTimes);

}
