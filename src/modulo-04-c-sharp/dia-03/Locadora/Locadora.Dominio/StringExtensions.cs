namespace System
{
    public static class StringExtensions
    {
        public static string FormatTo80(this string self)
        {
            int len = 40 + (self.Length / 2);
            return String.Format("{0," + len + "}", self);
        }
    }
}